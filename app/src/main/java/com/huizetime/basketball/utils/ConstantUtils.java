package com.huizetime.basketball.utils;

/**
 * Created by water_fairy on 2016/10/11.
 */

public class ConstantUtils {
    //左队/右队
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    //暂停/换人
    public static final int NO = -1;
    public static final int TING = 0;
    public static final int HUAN = 1;
    //fragment
    public static final int FRAGMENT_PREPARE = 0;
    public static final int FRAGMENT_PROCESS = 1;
    public static final int FRAGMENT_TEAM = 2;
    public static final int FRAGMENT_END = 3;

    //广播action
    public static final String ACTION_A_SCORE = "com.huizetime.basketball.a_score";//A队得分 左边得分
    public static final String ACTION_B_SCORE = "com.huizetime.basketball.b_score";//B队得分 右边得分
    public static final String ACTION_SEGMENT = "com.huizetime.basketball.segment";//小节
    public static final String ACTION_REQUEST_STOP = "com.huizetime.basketball.request_stop";//暂停 a暂停,b暂停(裁判暂停)
    public static final String ACTION_REQUEST_CHANGE_PLAYER = "com.huizetime.basketball.request_change_player";//请求换人 a换人,b换人
    public static final String ACTION_EVENT = "com.huizetime.basketball.event";//事件
    public static final String ACTION_TIME = "com.huizetime.basketball.time";//小节
    public static final String ACTION_CHANGE_AREA = "com.huizetime.basketball.change_area";//交换场地
    public static final String ACTION_CANCELLATION = "com.huizetime.basketball.cancellation";//比赛作废
    public static final String ACTION_GET_BALL = "com.huizetime.basketball.get_ball";//获取球权
    public static final String ACTION_SET_FRAGMENT = "com.huizetime.basketball.set_fragment";//设置4大fragment
    public static final String ACTION_STOP_TIMES = "com.huizetime.basketball.stop_times_";//暂停次数
    public static final String ACTION_FOUL_TIMES = "com.huizetime.basketball.foul_tmes";//犯规次数
    public static final String ACTION_STOP = "com.huizetime.basketball.stop";//暂停
    public static final String ACTION_CHANGE_PLAYER = "com.huizetime.basketball.change_player";//换人


}
