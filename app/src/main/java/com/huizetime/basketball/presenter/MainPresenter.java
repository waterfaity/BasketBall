package com.huizetime.basketball.presenter;

import android.app.Activity;

import com.huizetime.basketball.activity.WatchLoggingActivity;
import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.bean.tv.TVEventBean;
import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.database.TeamDB;
import com.huizetime.basketball.database.WatchDB;
import com.huizetime.basketball.listener.OnBelClickListener;
import com.huizetime.basketball.model.MainModel;
import com.huizetime.basketball.model.MainModelSimple;
import com.huizetime.basketball.utils.ConstantUtils;
import com.huizetime.basketball.utils.DialogUtils;
import com.huizetime.basketball.view.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class MainPresenter implements MainPresenterListener {

    private static final String TAG = "mainPresenter";
    private MainView mView;
    private Activity mActivity;
    private MainModel mModel;

    public MainPresenter(WatchLoggingActivity activity) {
        mView = activity;
        mActivity = activity;
        mModel = new MainModelSimple(this, activity);
    }

    @Override
    public void initData() {
        mModel.initData();
        int tvPage = MyApp.getApp().getTVCurrentPage();

        switch (tvPage) {
            case MyApp.PAGE_CONNECT:
                //未录入 未签到

                break;
            case MyApp.PAGE_SIGN:
                //签到

                break;
            case MyApp.PAGE_EVENT:
                //录入
             mModel.logIn();
                break;
        }

    }

    @Override
    public void sendWatchInfo() {
        mModel.sendWatchInfo(100, "上海圣杯之战", "梦之队", "星之队");
    }

    @Override
    public void sendImg() {
        mModel.sendImg(TVData.TYPE_A_LOGO, "/sdcard/jj.jpg");
    }

    @Override
    public void sendSign() {
        TVSignBean tvSignBean = new TVSignBean();
        TVSignBean.Entity aEntity = tvSignBean.new Entity();
        List<TVSignBean.Player> aList = new ArrayList<>();
        TVSignBean.Player aPlayer = tvSignBean.new Player();
        aPlayer.setSign(true);
        aPlayer.setPosition("前锋:");
        aPlayer.setNum(11);
        aPlayer.setName("郭靖");
        aList.add(aPlayer);
        aEntity.setList(aList);

        TVSignBean.Entity bEntity = tvSignBean.new Entity();
        List<TVSignBean.Player> bList = new ArrayList<>();
        TVSignBean.Player bPlayer = tvSignBean.new Player();
        bPlayer.setSign(true);
        bPlayer.setPosition("前锋:");
        bPlayer.setNum(11);
        bPlayer.setName("欧阳锋");
        bList.add(bPlayer);
        aEntity.setList(bList);

        mModel.sendSign(aEntity, bEntity);
    }

    @Override
    public void sendScore() {
        TVScoreBean tvScoreBean = new TVScoreBean();
        TVScoreBean.Entity aEntity = tvScoreBean.new Entity();
        aEntity.setFoul(4);
        aEntity.setScore(23);
        aEntity.setStopTime(2);

        TVScoreBean.Entity bEntity = tvScoreBean.new Entity();
        bEntity.setFoul(2);
        bEntity.setScore(65);
        bEntity.setStopTime(6);

        mModel.sendScoreInfo(aEntity, bEntity, 2, 11 * 6);

    }

    @Override
    public void change(int which, int type, int data) {
        mModel.sendChange(TVEventBean.TEAM_A, TVEventBean.SCORE, 26);
    }


    @Override
    public void back() {

        DialogUtils.show2(mActivity, "是否退出比赛录入界面?", new OnBelClickListener() {
            @Override
            public void onBelClick(boolean bel) {
                if (bel) {

                    mView.back();
                }
            }
        });


    }

    @Override
    public void destroy() {
        mModel.destroy();
    }

    @Override
    public void displayData(WatchDB watchDB, TeamDB aTeam, TeamDB bTeam) {
        mView.setTeamName(aTeam.getTeamName(), bTeam.getTeamName());
        mView.setScore(aTeam.getScore(), bTeam.getScore());
        mView.setSegment(watchDB.getSegment() + "");
        mView.setTime("12:00");
        mView.setTeamEventState(1, ConstantUtils.NO);

        mView.setATeamFoul(aTeam.getFoulTimes());


    }

}
