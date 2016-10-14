package com.huizetime.basketball.model;

import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.presenter.MainPresenter;

/**
 * Created by water_fairy on 2016/9/26.
 */
public interface MainModel {
    //初始化数据
    void initData();

    //作为用户端连接
    void connect();

    //发送比赛信息
    void sendWatchInfo(int watchId, String watchName, String aTeamName, String bTeamName);

    //发送图片
    void sendImg(int type, String path);

    //发送签到信息
    void sendSign(TVSignBean.Entity aEntity, TVSignBean.Entity bEntity);

    //发送记分板信息
    void sendScoreInfo(TVScoreBean.Entity aEntity, TVScoreBean.Entity bEntity, int segment, int time);

    //发送事件
    void sendChange(int which, int type, int data);

    //手动断开连接
    void close();

    //界面销毁
    void destroy();

    //录入中
    void logIn();
}
