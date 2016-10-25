package com.shmilysyp.kaishen.weatherapp;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by kaishen on 16/9/12.
 */

public interface LoaderService {

    @Headers("apikey : 22ca8832d8473387dc9424e83d940834")
    @GET("weather")
    Observable<BaseResponse<CityWeatherInfo>> getCityWeatherList(@Query("citypinyin") String s);//获取城市天气
}
