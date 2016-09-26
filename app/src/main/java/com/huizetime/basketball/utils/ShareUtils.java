package com.huizetime.basketball.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.huizetime.basketball.application.MyApp;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class ShareUtils {
    public static SharedPreferences getSetting() {
        Context context = MyApp.getApp().getApplicationContext();
        return context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    public static String getBTAddress() {
        return getSetting().getString("address", "");
    }
}
