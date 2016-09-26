package com.huizetime.basketball.model;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;

import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.manager.ConnectManager;
import com.huizetime.basketball.manager.TVDataManager;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.utils.PermissionUtils;
import com.huizetime.basketball.utils.ShareUtils;
import com.huizetime.basketball.utils.ToastUtils;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class MainModelSimple implements MainModel {

    private MainPresenter mPresenter;
    private Activity mActivity;
    private ConnectManager mConnectManager;
    private TVDataManager mTVDataManager;

    public MainModelSimple(MainPresenter presenter, Activity activity) {
        this.mPresenter = presenter;
        mActivity = activity;
    }

    @Override
    public void initData() {
        //初始化权限
        PermissionUtils.requestPermission(mActivity, PermissionUtils.REQUEST_LOCATION);
        //初始化蓝牙连接管理
        mConnectManager = ConnectManager.getInstance();
        //初始化数据传输管理
        mTVDataManager = new TVDataManager(MyApp.getApp().getBTManager());
    }

    @Override
    public void connect(MainPresenter mainPresenter) {
        String address = ShareUtils.getBTAddress();
        if (TextUtils.isEmpty(address)) {
            ToastUtils.show("未设置蓝牙地址");
        } else {
            BluetoothDevice device = MyApp.getApp().getBTManager()
                    .getBTAdapter()
                    .getRemoteDevice(address);
            mConnectManager.setUserListener(new ConnectManager.UserConnectListener() {
                @Override
                public void onConnectSuccess() {
                    mPresenter.onConnectSuccess();
                }

                @Override
                public void onConnecting() {
                    mPresenter.onConnecting();

                }

                @Override
                public void onDisconnect() {
                    mPresenter.onDisconnect();

                }

                @Override
                public void onConnectError() {
                    mPresenter.onConnectError();

                }

                @Override
                public void onWrite(byte[] bytes) {
                    mPresenter.onWrite(bytes);
                }
            });
            mConnectManager.setAsUser(device);

        }

    }

    @Override
    public void sendWatchInfo(String watchName, String aTeamName, String bTeamName) {
        mTVDataManager.setWatchName(watchName);
        mTVDataManager.setATeamName(aTeamName);
        mTVDataManager.setBTeamName(bTeamName);
        mTVDataManager.senSignPlayer(null, null);
    }

    @Override
    public void sendImg(int type, String path) {
        mTVDataManager.sendImg(type, path);
    }

    @Override
    public void sendSign(TVSignBean.Entity aEntity, TVSignBean.Entity bEntity) {
        mTVDataManager.senSignPlayer(aEntity, bEntity);

    }

    @Override
    public void sendMainInfo(TVScoreBean.Entity aEntity, TVScoreBean.Entity bEntity, int segment, int time) {
        mTVDataManager.sendInitScoreData(aEntity, bEntity, segment, time);
    }

    @Override
    public void sendChange(int which, int type, int data) {
        mTVDataManager.change(which, type, data);
    }

    @Override
    public void close() {
        mTVDataManager.close();
    }
}
