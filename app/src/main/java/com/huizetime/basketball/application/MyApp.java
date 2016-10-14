package com.huizetime.basketball.application;

import android.app.Application;
import android.content.res.Configuration;

import com.huizetime.basketball.manager.BTManager;
import com.huizetime.basketball.manager.TVDataSendManager;
import com.orm.SugarApp;
import com.orm.SugarContext;

/**
 * Created by water_fairy on 2016/9/22.
 */
public class MyApp extends SugarApp{

    private BTManager btManager;
    private static MyApp myApp;
    private TVDataSendManager tvDataSendManager;
    public static final int PAGE_CONNECT = 1;
    public static final int PAGE_SIGN = 2;
    public static final int PAGE_EVENT = 3;


    private int mTVCurrentPage = PAGE_CONNECT;
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;

    }

    public static MyApp getApp() {
        return myApp;
    }

    public BTManager getBTManager() {
        if (btManager == null) {
            btManager = BTManager.getInstance().initData(getApplicationContext());
        }
        return btManager;
    }

    public TVDataSendManager getTVDataSendManager() {
        if (tvDataSendManager == null) {
            tvDataSendManager = new TVDataSendManager(getBTManager());
        }
        return tvDataSendManager;
    }

    public int getTVCurrentPage() {
        return mTVCurrentPage;
    }

    public void setTVCurrentPage(int mTVCurrentPage) {


        this.mTVCurrentPage = mTVCurrentPage;
    }


}
