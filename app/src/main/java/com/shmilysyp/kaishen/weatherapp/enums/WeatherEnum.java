package com.shmilysyp.kaishen.weatherapp.enums;

import com.shmilysyp.kaishen.weatherapp.R;

/**
 * Created by kaishen on 16/10/26.
 */

public enum WeatherEnum {
    Sunny("晴", R.drawable.weather_fine),
    Cloudy("多云", R.drawable.weather_cloudy),
    Overcast("阴", R.drawable.weather_overcast),
    Shower("阵雨", R.drawable.weather_shower),
    Thundershower("雷阵雨", R.drawable.weather_thundershower),
    ThundershowerWithHail("雷阵雨伴有冰雹", R.drawable.weather_thundershower_with_hail),
    Sleet("雨夹雪", R.drawable.weather_sleet),
    LightRain("小雨", R.drawable.weather_light_rain),
    ModerateRain("中雨", R.drawable.weather_moderate_rain),
    HeavyRain("大雨", R.drawable.weather_heavy_rain),
    Strom("暴雨", R.drawable.weather_heavy_rainstorm),
    HeavyStrom("大暴雨", R.drawable.weather_rainstorm),
    SeverStrom("特大暴雨", R.drawable.weather_extraordinary_rainstorm),
    SnowFlurry("阵雪", R.drawable.weather_snow_shower),
    LightSnow("小雪", R.drawable.weather_light_snow),
    ModerateSnow("中雪", R.drawable.weather_moderate_snow),
    HeavySnow("大雪", R.drawable.weather_heavy_snow),
    SnowStrom("暴雪", R.drawable.weather_snowstorm),
    Foggy("雾", R.drawable.weather_fog),
    IceRain("冻雨", R.drawable.weather_freezing_rain),
    Dustsorm("沙尘暴", R.drawable.weather_sandstorm),
    LightToModerateRain("小到中雨", R.drawable.weather_fine),
    ModerateToHeavyRain("中到大雨", R.drawable.weather_fine),
    HeavyRainToStrom("大到暴雨", R.drawable.weather_fine),
    StromToHeavyStrom("暴雨到大暴雨", R.drawable.weather_fine),
    HeavyToSeverStrom("大暴雨到特大暴雨", R.drawable.weather_fine),
    LightToModerateSnow("小到中雪", R.drawable.weather_fine),
    ModerateToHeavySnow("中到大雪", R.drawable.weather_fine),
    HeavySnowToStrom("大到暴雪", R.drawable.weather_fine),
    Dust("浮尘", R.drawable.weather_floating_dust),
    Sand("扬沙", R.drawable.weather_blowing_sand),
    SandStrom("强沙尘暴", R.drawable.weather_strong_sandstorm),
    Haze("霾", R.drawable.weather_haze),
    Unknow("无", 0);

    public String desc;
    public int iconId;

    WeatherEnum(String desc, int iconId) {
        this.desc = desc;
        this.iconId = iconId;
    }

    public String getDesc() {
        return desc;
    }

    public int getIconId() {
        return iconId;
    }

    public static WeatherEnum stringValueOf(String desc) {
        switch (desc) {
            case "晴":
                return Sunny;
            case "多云":
                return Cloudy;
            case "阴":
                return Overcast;
            case "阵雨":
                return Shower;
            case "雷阵雨":
                return Thundershower;
            case "雷阵雨伴有冰雹":
                return ThundershowerWithHail;
            case "雨夹雪":
                return Sleet;
            case "小雨":
                return LightRain;
            case "中雨":
                return ModerateRain;
            case "大雨":
                return HeavyRain;
            case "暴雨":
                return Strom;
            case "大暴雨":
                return HeavyStrom;
            case "特大暴雨":
                return SeverStrom;
            case "阵雪":
                return SnowFlurry;
            case "小雪":
                return LightSnow;
            case "中雪":
                return ModerateSnow;
            case "大雪":
                return HeavySnow;
            case "暴雪":
                return SnowStrom;
            case "雾":
                return Foggy;
            case "冻雨":
                return IceRain;
            case "沙尘暴":
                return Dustsorm;
            case "小到中雨":
                return LightToModerateRain;
            case "中到大雨":
                return ModerateToHeavyRain;
            case "大到暴雨":
                return HeavyRainToStrom;
            case "暴雨到大暴雨":
                return StromToHeavyStrom;
            case "大暴雨到特大暴雨":
                return HeavyRainToStrom;
            case "小到中雪":
                return LightToModerateSnow;
            case "中到大雪":
                return ModerateToHeavySnow;
            case "浮尘":
                return Dust;
            case "扬沙":
                return Sand;
            case "强沙尘暴":
                return SandStrom;
            case "霾":
                return Haze;
            default:
                return Unknow;
        }
    }
}
