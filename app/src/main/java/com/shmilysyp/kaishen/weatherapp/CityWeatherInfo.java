package com.shmilysyp.kaishen.weatherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by kaishen on 16/10/18.
 */

public class CityWeatherInfo implements Serializable{

    @SerializedName("city")
    @Expose
    public String cityName;//城市

    @SerializedName("pinyin")
    @Expose
    public String pinYin;//城市拼音

    @SerializedName("cityCode")
    @Expose
    public String cityCode;//城市编码

    @SerializedName("date")
    @Expose
    public String date;//日期

    @SerializedName("time")
    @Expose
    public String time;//发布时间

    @SerializedName("postCode")
    @Expose
    public String postCode;//邮编

    @SerializedName("longitude")
    @Expose
    public String longitude;//经度

    @SerializedName("latitude")
    @Expose
    public String latitude;//纬度

    @SerializedName("altitude")
    @Expose
    public String altitude;//海拔

    @SerializedName("weather")
    @Expose
    public String weather;//天气

    @SerializedName("temp")
    @Expose
    public String temp;//温度

    @SerializedName("l_temp")
    @Expose
    public String lTemp;//最低温度

    @SerializedName("h_temp")
    @Expose
    public String hTemp;//最高温度

    @SerializedName("WD")
    @Expose
    public String wd;//风向

    @SerializedName("WS")
    @Expose
    public String ws;//风力

    @SerializedName("sunrise")
    @Expose
    public String sunrise;//日出时间

    @SerializedName("sunset")
    @Expose
    public String sunset;//日落时间

}
