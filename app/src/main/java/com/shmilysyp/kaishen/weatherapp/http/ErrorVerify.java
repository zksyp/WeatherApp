package com.shmilysyp.kaishen.weatherapp.http;

/**
 * Created by kaishen on 16/10/18.
 */

public interface ErrorVerify {
    void call(String code,String desc);

    void netError(Throwable throwable);
}
