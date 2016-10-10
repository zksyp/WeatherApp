package com.shmilysyp.kaishen.weatherapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kaishen on 16/9/13.
 */

public class LogUtil {

    private static String DEFAULT_TAG = "toys";

    public static void v(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            largeLogV(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            largeLogI(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (BuildConfig.DEBUG) {
            largeLogE(tag, msg);
        }
    }


    public static void v(String msg) {
        if (BuildConfig.DEBUG) {
            largeLogV(DEFAULT_TAG, msg);
        }
    }

    public static void i(String msg) {
        if (BuildConfig.DEBUG) {
            largeLogI(DEFAULT_TAG, msg);
        }
    }

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            largeLogE(DEFAULT_TAG, msg);
        }
    }

    public static void largeLogI(String tag, String content) {
        if (content != null && !" ".equals(content)) {
            if (content.length() > 3000) {
                Log.i(tag, content.substring(0, 3000));
                largeLogI(tag, content.substring(3000));
            } else {
                Log.i(tag, content);
            }
        }
    }

    public static void largeLogV(String tag, String content) {
        if (content != null && !" ".equals(content)) {
            if (content.length() > 3000) {
                Log.v(tag, content.substring(0, 3000));
                largeLogV(tag, content.substring(3000));
            } else {
                Log.v(tag, content);
            }
        }
    }

    public static void largeLogE(String tag, String content) {
        if (content != null && !" ".equals(content)) {
            if (content.length() > 3000) {
                Log.e(tag, content.substring(0, 3000));
                largeLogE(tag, content.substring(3000));
            } else {
                Log.e(tag, content);
            }
        }
    }

    public static void json(String tag, String json) {
        int indent = 4;
        if (TextUtils.isEmpty(json)) {
            e(tag, "JSON{json is empty}");
            return;
        }
        try {
            if (json.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(json);
                String msg = jsonObject.toString(indent);
                e(tag, msg);
            } else if (json.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(json);
                String msg = jsonArray.toString(indent);
                e(tag, msg);
            }
        } catch (JSONException e) {
            e(tag, e.toString() + "\n\njson = " + json);
        }
    }
}
