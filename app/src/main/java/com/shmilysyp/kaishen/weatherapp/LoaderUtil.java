package com.shmilysyp.kaishen.weatherapp;

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

    public static LoaderService getLoaderService(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpUtil.BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(AsyncOkHttpClient.getClient())
        }
        return null;
    }

}
