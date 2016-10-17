package com.huizetime.basketball.utils;

import android.content.ContentUris;
import android.content.Intent;

import com.huizetime.basketball.application.MyApp;

/**
 * Created by water_fairy on 2016/10/17.
 */

public class BroadcastUtils {
    private static MyApp myApp = MyApp.getApp();

    /**
     * 交换场地
     */
    public static void changeArea() {
        myApp.sendBroadcast(new Intent(ConstantUtils.ACTION_CHANGE_AREA));
    }

    /**
     * 比赛作废
     */
    public static void cancellation() {
        myApp.sendBroadcast(new Intent(ConstantUtils.ACTION_CANCELLATION));
    }

    /**
     * 获取球权
     *
     * @param which
     * @param playerNum
     */
    public static void getBall(int which, int playerNum) {
        Intent intent = new Intent(ConstantUtils.ACTION_GET_BALL);
        intent.putExtra("data", which);
        intent.putExtra("extra", playerNum);
        myApp.sendBroadcast(intent);
    }

    /**
     * 设置4大fragment
     *
     * @param pos
     */
    public static void setFragment(int pos) {
        Intent intent = new Intent(ConstantUtils.ACTION_SET_FRAGMENT);
        intent.putExtra("data", pos);
        myApp.sendBroadcast(intent);
    }
}
