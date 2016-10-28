package com.shmilysyp.kaishen.weatherapp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by kaishen on 16/9/12.
 */

public class LoaderUtil {

    private static Retrofit retrofit;
    private static LoaderService service;

    private static final int DEFAULT_TIMEOUT = 5;

    public static LoaderService getLoaderService() {


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
//                    .client(httpClientBuilder.build())
                    .baseUrl(HttpUtil.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        if (service == null) {
            service = retrofit.create(LoaderService.class);
        }
        return service;
    }

    //封装线程过滤
    public static <T> Observable.Transformer<T, T> onNext() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 请求结果错误过滤
     * @param errorVerify
     * @param <T>
     * @return
     */
    public static <T> Func1<T, Boolean> verifyNetError(final ErrorVerify errorVerify) {
        return new Func1<T, Boolean>() {
            @Override
            public Boolean call(T response) {
                if (response instanceof BaseResponse) {
                    BaseResponse baseResponse = (BaseResponse) response;
                    ResponseCode responseCode = ResponseCode.toEnum(baseResponse.mErrNum);
                    boolean isSuccess = responseCode == ResponseCode.RESULT_SUC;
                    if (!isSuccess) {
                        if (null != errorVerify) {
                            LogUtil.e("返回错误码：" + baseResponse.mErrNum + "\t\t\t错误信息：" + baseResponse.mErrMsg);
                            errorVerify.call(baseResponse.mErrNum, baseResponse.mErrMsg);
                        }
                    }
                    return isSuccess;
                } else {
                    return false;
                }
            }
        };
    }

//    public static <T> Observable.Transformer<T, T> filterError(final ErrorVerify errorVerify){
//
//        return new Observable.Transformer<T, T>() {
//            @Override
//            public Observable<T> call(Observable<T> observable) {
//                return observable
//                        .compose(LoaderUtil.onNext())
//                        .filter(verifyNetError(errorVerify));
//            }
//        };
//
//    }

}
