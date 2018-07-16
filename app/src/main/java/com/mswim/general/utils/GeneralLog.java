package com.mswim.general.utils;

import android.util.Log;

import com.mswim.general.BuildConfig;

public class GeneralLog {

    public static void i(String TAG, String value) {
        if (BuildConfig.DEBUG)
            Log.i(TAG, value);
    }

    public static void e(String TAG, String value) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, value);
    }

    public static void v(String TAG, String value) {
        if (BuildConfig.DEBUG)
            Log.v(TAG, value);
    }

}
