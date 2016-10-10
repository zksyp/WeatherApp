package com.shmilysyp.kaishen.weatherapp;

/**
 * Created by kaishen on 16/9/12.
 */

public class HttpUtil {

    public static final int NETWORK_ERROR = 0; // 网络错误标识位

    public static final int NETWORK_GPRS = 1;// 2G网络标识位

    public static final int NETWORK_WIFI = 2;// wifi网络标识位

    // 0:无网络 1: gprs 2: wifi
    public static int connectState = 0; // 当前网络状态

    final static String BASE_URL = "http://apis.baidu.com/apistore/weatherservice/";
}
