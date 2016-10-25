package com.shmilysyp.kaishen.weatherapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kaishen on 16/10/25.
 */

public class RecentWeathersEntity {

    @SerializedName("city")
    @Expose
    public String city ;//城市

    @SerializedName("cityid")
    @Expose
    public String cityid ;//城市id

    @SerializedName("today")
    @Expose
    public WeatherEntity todayWeather;//今日天气

    @SerializedName("forecast")
    @Expose
    public List<WeatherEntity> forecastWeather;//最近七天天气

}
