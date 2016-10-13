package com.huizetime.basketball.utils;

import com.huizetime.basketball.R;
import com.huizetime.basketball.application.MyApp;

/**
 * Created by water_fairy on 2016/10/13.
 */

public class StringUtils {
    public static String getString(int resId) {
        return MyApp.getApp().getResources().getString(resId);
    }
}
