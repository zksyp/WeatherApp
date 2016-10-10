package com.shmilysyp.kaishen.weatherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kaishen on 16/9/12.
 */

public class BaseRequest {

    @SerializedName("apikey")
    @Expose
    public String apikey = "22ca8832d8473387dc9424e83d940834";
}
