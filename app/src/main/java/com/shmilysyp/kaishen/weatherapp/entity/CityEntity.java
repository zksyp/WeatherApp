package com.shmilysyp.kaishen.weatherapp.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kaishen on 16/10/28.
 */

public class CityEntity {

    @SerializedName("cityName")
    @Expose
    public String cityName ;//城市名称

    @SerializedName("provinceName")
    @Expose
    public String provinceName ;//省名

    @SerializedName("cityCode")
    @Expose
    public String cityCode ;//城市代码

    @SerializedName("zipCode")
    @Expose
    public String zipCode ;//邮编

    @SerializedName("telAreaCode")
    @Expose
    public String telAreaCode ;//电话区号
}
