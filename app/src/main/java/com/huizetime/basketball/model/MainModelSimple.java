package com.huizetime.basketball.model;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.manager.TVDataSendManager;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.utils.ConstantUtils;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class MainModelSimple implements MainModel {

    private static final String TAG = "mainModel";
    private MainPresenter mPresenter;
    private Activity mActivity;
    private TVDataSendManager mTVDataSendManager;


    public MainModelSimple(MainPresenter presenter, Activity activity) {
        this.mPresenter = presenter;
        mActivity = activity;
    }

    @Override
    public void initData() {

        //初始化数据传输管理
        mTVDataSendManager = MyApp.getApp().getTVDataSendManager();
        //初始化广播
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConstantUtils.ACTION_A_SCORE);
        mActivity.registerReceiver(mDataReceiver, intentFilter);
    }

    @Override
    public void connect() {

    }

    @Override
    public void sendWatchInfo(int watchId, String watchName, String aTeamName, String bTeamName) {
        mTVDataSendManager.setWatchId(watchId);
        mTVDataSendManager.setWatchName(watchName);
        mTVDataSendManager.setATeamName(aTeamName);
        mTVDataSendManager.setBTeamName(bTeamName);
        mTVDataSendManager.senSignPlayer(null, null);
    }

    @Override
    public void sendImg(int type, String path) {
        mTVDataSendManager.sendImg(type, path);
    }

    @Override
    public void sendSign(TVSignBean.Entity aEntity, TVSignBean.Entity bEntity) {
        mTVDataSendManager.senSignPlayer(aEntity, bEntity);

    }

    @Override
    public void sendScoreInfo(TVScoreBean.Entity aEntity, TVScoreBean.Entity bEntity, int segment, int time) {
        mTVDataSendManager.sendInitScoreData(aEntity, bEntity, segment, time);
    }

    @Override
    public void sendChange(int which, int type, int data) {
        mTVDataSendManager.change(which, type, data);
    }

    @Override
    public void close() {
        mTVDataSendManager.close();

    }


    @Override
    public void destroy() {
        mActivity.unregisterReceiver(mDataReceiver);
    }

    private BroadcastReceiver mDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {

            }
        }
    };
}
