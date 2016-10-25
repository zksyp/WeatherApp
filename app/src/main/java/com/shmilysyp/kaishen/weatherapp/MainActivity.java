package com.shmilysyp.kaishen.weatherapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import rx.Subscription;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    protected final CompositeSubscription mPendingSubscriptions = new CompositeSubscription();
    private CityWeatherInfo mWeatherInfo = new CityWeatherInfo();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String apiKey = "22ca8832d8473387dc9424e83d940834";

        CityWeatherRequest cityWeatherRequest = new CityWeatherRequest();
        cityWeatherRequest.citypinyin = "beijing";

        Subscription subscription = LoaderUtil.getLoaderService()
                .getCityWeatherList("beijing")
                .compose(LoaderUtil.filterError(new ErrorVerify() {
                    @Override
                    public void call(String code, String desc) {
                        LogUtil.e(code +"===", desc);
                    }

                    @Override
                    public void netError(Throwable throwable) {

                    }
                }))
                .subscribe(new Action1<Object>() {
                });
//                        (new Subscriber<BaseResponse<CityWeatherInfo>>() {
//                    @Override
//                    public void onCompleted() {
//                        LogUtil.e("===", "success");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        LogUtil.e("===", "error");
//                    }
//
//                    @Override
//                    public void onNext(BaseResponse<CityWeatherInfo> cityWeatherInfoBaseResponse) {
//                        LogUtil.e("result", cityWeatherInfoBaseResponse.retData.date);
//                    }
//                });
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(HttpUtil.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

//        LoaderService service = retrofit.create(LoaderService.class);
//        Call<BaseResponse<CityWeatherInfo>> call = service.getCityWeatherList(apiKey, "beijing");
//        call.enqueue(new Callback<BaseResponse<CityWeatherInfo>>() {
//            @Override
//            public void onResponse(Call<BaseResponse<CityWeatherInfo>> call, Response<BaseResponse<CityWeatherInfo>> response) {
//                LogUtil.e("===", "return" + response.body().retData.date);
//            }
//
//            @Override
//            public void onFailure(Call<BaseResponse<CityWeatherInfo>> call, Throwable t) {
//                LogUtil.e("===", "fail");
//            }
//        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        mClient.connect();
        AppIndex.AppIndexApi.start(mClient, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(mClient, getIndexApiAction());
        mClient.disconnect();
    }
}
