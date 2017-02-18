package com.shmilysyp.kaishen.weatherapp.entity;

/**
 * Created by kaishen on 16/10/18.
 */

public enum ResponseCode {

    RESULT_SUC(0, "请求成功"),
    RESULT_APIKEY_NEED(300003, "未传入APIkey"),
    RESULT_FAILED(3,"请求异常");


    private Integer mCode;
    private String mReason;

    ResponseCode(Integer code, String reason){
        this.mCode = code;
        this.mReason = reason;
    }

    private static ResponseCode valueOf(Integer code){
        switch (code){
            case 0:
                return RESULT_SUC;
            case 300003:
                return RESULT_APIKEY_NEED;
            default:
                return RESULT_FAILED;
        }
    }

    public static ResponseCode toEnum(String code){
        if(code == null || code.equals("")){
            return RESULT_FAILED;
        }
        return valueOf(Integer.parseInt(code));
    }

    public boolean isSuccess(){return this == RESULT_SUC;}
}
