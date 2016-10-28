package com.shmilysyp.kaishen.weatherapp;

import android.app.Application;

/**
 * Created by kaishen on 16/10/27.
 */

public class WeatherApp extends Application{

    private static WeatherApp app;

    public static WeatherApp getApp(){
        return app;
    }
}
