package com.huizetime.basketball.model;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.bean.tv.TVScoreBean;
import com.huizetime.basketball.bean.tv.TVSignBean;
import com.huizetime.basketball.manager.BTDataTrans;
import com.huizetime.basketball.manager.ConnectManager;
import com.huizetime.basketball.manager.TVDataReceiveUtils;
import com.huizetime.basketball.manager.TVDataSendManager;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.utils.PermissionUtils;
import com.huizetime.basketball.utils.ShareUtils;
import com.huizetime.basketball.utils.ToastUtils;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class MainModelSimple implements MainModel {

    private static final String TAG = "mainModel";
    private MainPresenter mPresenter;
    private Activity mActivity;
    private ConnectManager mConnectManager;
    private TVDataSendManager mTVDataSendManager;
    private BTDataTrans mBTDataTrans;

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
        mTVDataSendManager = new TVDataSendManager(MyApp.getApp().getBTManager());
        //数据接收转换器
        mBTDataTrans = new BTDataTrans();
    }

    @Override
    public void connect(MainPresenter mainPresenter) {
        String address = ShareUtils.getBTAddress();
        if (TextUtils.isEmpty(address)) {
            ToastUtils.show("未设置蓝牙地址");
        } else {
            BluetoothDevice device = MyApp.getApp().getBTManager()
                    .initBTAdapter()
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

                @Override
                public void onRead(byte[] bytes, int len) {
                    String json = TVDataReceiveUtils.receive(mBTDataTrans, bytes, len);
                    Log.i(TAG, "onRead: "+json);
                    if (json != null) {
                        TVData tvData = new Gson().fromJson(json, TVData.class);
                        if (tvData.getCode() == TVData.TYPE_RESULT) {
                            Log.i(TAG, "onRead: resultCode " + tvData.getResult());
                            mTVDataSendManager.setResultCode(tvData.getResult());
                        }
                    }
                }
            });
            mConnectManager.setAsUser(device);

        }

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
}
