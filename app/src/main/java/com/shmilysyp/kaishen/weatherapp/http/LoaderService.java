package com.shmilysyp.kaishen.weatherapp.http;

import com.shmilysyp.kaishen.weatherapp.entity.BaseResponse;
import com.shmilysyp.kaishen.weatherapp.entity.CityEntity;
import com.shmilysyp.kaishen.weatherapp.entity.CityWeatherInfo;
import com.shmilysyp.kaishen.weatherapp.entity.RecentWeathersEntity;

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

    @Headers("apikey : 22ca8832d8473387dc9424e83d940834")
    @GET("recentweathers")
    Observable<BaseResponse<RecentWeathersEntity>> getRecentWeathers(@Query("cityname") String name,
                                                                     @Query("cityid") String id);//获取城市最近几天的天气

    @Headers("apikey : 22ca8832d8473387dc9424e83d940834")
    @GET("cityinfo")
    Observable<BaseResponse<CityEntity>> getCityInfo(@Query("cityname") String s);//获取城市信息


}
