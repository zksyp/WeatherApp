package com.shmilysyp.kaishen.weatherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kaishen on 16/10/18.
 */

public class CityWeatherRequest extends BaseRequest {

    @SerializedName("citypinyin")
    @Expose
    public String citypinyin;

}
