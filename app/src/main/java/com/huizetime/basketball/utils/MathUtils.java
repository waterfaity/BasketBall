package com.huizetime.basketball.utils;

import java.text.DecimalFormat;

/**
 * Created by water_fairy on 2016/9/27.
 */
public class MathUtils {
    public static String getPoint2(double num) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(num);
    }
}
