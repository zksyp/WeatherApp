package com.shmilysyp.kaishen.weatherapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kaishen on 16/10/25.
 */

public class WeatherEntity {

    @SerializedName("date")
    @Expose
    public String date ;//日期

    @SerializedName("week")
    @Expose
    public String week ;//星期

    @SerializedName("curTemp")
    @Expose
    public String curTemp ;//当前温度

    @SerializedName("aqi")
    @Expose
    public String aqi ;//pm值

    @SerializedName("fengxiang")
    @Expose
    public String fengxiang ;//风向

    @SerializedName("fengli")
    @Expose
    public String fengli ;//风力

    @SerializedName("hightemp")
    @Expose
    public String hightemp ;//最高温度

    @SerializedName("lowtemp")
    @Expose
    public String lowtemp ;//最低温度

    @SerializedName("type")
    @Expose
    public String type ;//天气类型

    //    @SerializedName("index")
//    @Expose
//    public List<String> index ;//指标列表
}
