package com.huizetime.basketball.utils;

/**
 * Created by water_fairy on 2016/10/11.
 */

public class ConstantUtils {
    //左队/右队
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    //暂停/换人
    public static final int TING = 0;
    public static final int HUAN = 1;

    //广播action
    public static final String ACTION_A_SCORE = "com.huizetime.basketball.a_score";//A队得分 左边得分
    public static final String ACTION_B_SCORE = "com.huizetime.basketball.b_score";//B队得分 右边得分
    public static final String ACTION_SEGMENT = "com.huizetime.basketball.segment";//小节
    public static final String ACTION_STOP = "com.huizetime.basketball.stop";//暂停 a暂停,b暂停(裁判暂停)
    public static final String ACTION_CHANGE = "com.huizetime.basketball.change";//换人 a换人,b换人
    public static final String ACTION_EVENT = "com.huizetime.basketball.event";//事件

}
