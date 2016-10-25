package com.shmilysyp.kaishen.weatherapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kaishen on 16/10/18.
 */

public class BaseResponse<T> {

    /**
     * 返回码，见ResponseCode枚举
     */
    @SerializedName("errNum")
    @Expose
    public String mErrNum;

    //错误描述
    @SerializedName("errMsg")
    @Expose
    public String mErrMsg;

    @SerializedName("retData")
    @Expose
    public T retData;
}
