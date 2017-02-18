package com.shmilysyp.kaishen.weatherapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kaishen on 16/9/12.
 */

public class CityInfo extends BaseRequest {

    @SerializedName("cityname")
    @Expose
    public String cityname ;
}
