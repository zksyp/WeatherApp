package com.shmilysyp.kaishen.weatherapp.enums;

/**
 * Created by kaishen on 16/10/25.
 */

public enum WeekEnum implements BaseEnum {
    Sunday("星期天", 0, "SUN"),
    Monday("星期一", 1, "MON"),
    Tuesday("星期二", 2, "TUE"),
    Wednesday("星期三", 3, "WED"),
    Thursday("星期四", 4, "THU"),
    Friday("星期五", 5, "FRI"),
    Saturday("星期六", 6, "SAT"),
    Unknow("未知", 7, "UNK");

    public String desc;

    public int status;

    public String abbr;

    WeekEnum(String desc, int status, String abbr) {
        this.desc = desc;
        this.status = status;
        this.abbr = abbr;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public String getAbbr(){
        return abbr;
    }

    public static WeekEnum valueOf(int status){
        switch (status){
            case 0:
                return Sunday;
            case 1:
                return Monday;
            case 2:
                return Tuesday;
            case 3:
                return Wednesday;
            case 4:
                return Thursday;
            case 5:
                return Friday;
            case 6:
                return Saturday;
            default:
                return Unknow;
        }
    }

    public static WeekEnum stringValueOf(String desc){
        switch (desc){
            case "星期天":
                return Sunday;
            case "星期一":
                return Monday;
            case "星期二":
                return Tuesday;
            case "星期三":
                return Wednesday;
            case "星期四":
                return Thursday;
            case "星期五":
                return Friday;
            case "星期六":
                return Saturday;
            default:
                return Unknow;
        }
    }
}
