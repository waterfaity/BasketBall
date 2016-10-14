package com.huizetime.basketball.presenter;

import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.database.TeamDB;
import com.huizetime.basketball.database.WatchDB;

/**
 * Created by water_fairy on 2016/9/26.
 */
public interface MainPresenterListener extends BasePresenter{
    void initData();


    void sendWatchInfo();

    void sendImg();

    void sendSign();

    void sendScore();

    void change(int which, int type, int data);


    void destroy();

    void displayData(WatchDB watchDB, TeamDB aTeam, TeamDB bTeam);
}
