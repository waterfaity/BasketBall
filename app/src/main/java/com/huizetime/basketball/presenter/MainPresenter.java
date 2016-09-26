package com.huizetime.basketball.presenter;

import android.app.Activity;
import android.graphics.Paint;

import com.huizetime.basketball.activity.MainActivity;
import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.bean.tv.TVEventBean;
import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.manager.ConnectManager;
import com.huizetime.basketball.model.MainModel;
import com.huizetime.basketball.model.MainModelSimple;
import com.huizetime.basketball.view.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class MainPresenter implements MainPresenterListener, ConnectManager.UserConnectListener {

    private MainView mView;
    private Activity mActivity;
    private MainModel mModel;


    public MainPresenter(MainActivity activity) {
        mView = activity;
        mActivity = activity;
        mModel = new MainModelSimple(this, activity);
    }

    @Override
    public void initData() {
        mModel.initData();
    }

    @Override
    public void connect() {
        mModel.connect(this);
    }

    @Override
    public void onConnectSuccess() {

    }

    @Override
    public void onConnecting() {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onConnectError() {

    }

    @Override
    public void onWrite(byte[] bytes) {

    }

    @Override
    public void setWatchInfo() {
        mModel.sendWatchInfo("上海圣杯之战", "梦之队", "星之队");
    }

    @Override
    public void sendImg(int type, String path) {
        mModel.sendImg(type, path);

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

        mModel.sendMainInfo(aEntity, bEntity, 2, 11 * 6);

    }

    @Override
    public void change() {
        mModel.sendChange(TVEventBean.TEAM_A, TVEventBean.SCORE, 26);
    }

    @Override
    public void close() {
        mModel.close();
    }

}