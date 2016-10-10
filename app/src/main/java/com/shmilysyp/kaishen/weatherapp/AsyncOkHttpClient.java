package com.shmilysyp.kaishen.weatherapp;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by kaishen on 16/9/12.
 */

public class AsyncOkHttpClient {
    public static final String TAG = AsyncOkHttpClient.class.getSimpleName();

    private static final int TIMEOUT = 30_000;

    private static OkHttpClient SINGLETON;


    private AsyncOkHttpClient() {
    }

    public static OkHttpClient getClient() {
        if (SINGLETON == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            SINGLETON = new OkHttpClient
                    .Builder()
                    .addInterceptor(httpLoggingInterceptor)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT,TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT,TimeUnit.SECONDS)
                    .build();
        }
        return SINGLETON;
    }
}
