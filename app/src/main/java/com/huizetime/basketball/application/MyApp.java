package com.huizetime.basketball.application;

import android.app.Application;
import android.bluetooth.BluetoothDevice;

import com.huizetime.basketball.manager.BTManager;
import com.huizetime.basketball.manager.TVDataManager;
import com.orm.SugarApp;

import java.util.Deque;

/**
 * Created by water_fairy on 2016/9/22.
 */
public class MyApp extends SugarApp {

    private BTManager btManager;
    private static MyApp myApp;
    private TVDataManager tvDataManager;

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

    public TVDataManager getTVDataManager() {
        if (tvDataManager == null) {
            tvDataManager = new TVDataManager(getBTManager());
        }
        return tvDataManager;
    }


}
