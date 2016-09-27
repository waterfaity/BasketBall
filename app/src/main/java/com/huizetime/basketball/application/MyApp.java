package com.huizetime.basketball.application;

import com.huizetime.basketball.manager.BTManager;
import com.huizetime.basketball.manager.TVDataSendManager;
import com.orm.SugarApp;

/**
 * Created by water_fairy on 2016/9/22.
 */
public class MyApp extends SugarApp {

    private BTManager btManager;
    private static MyApp myApp;
    private TVDataSendManager tvDataSendManager;

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

    public TVDataSendManager getTVDataManager() {
        if (tvDataSendManager == null) {
            tvDataSendManager = new TVDataSendManager(getBTManager());
        }
        return tvDataSendManager;
    }


}
