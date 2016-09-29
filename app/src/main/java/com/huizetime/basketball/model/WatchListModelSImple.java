package com.huizetime.basketball.model;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.huizetime.basketball.activity.WatchListActivity;
import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.WatchBean;
import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.manager.ConnectManager;
import com.huizetime.basketball.manager.TVDataReceiveUtils;
import com.huizetime.basketball.presenter.WatchListPresenter;
import com.huizetime.basketball.utils.ShareUtils;
import com.huizetime.basketball.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by water_fairy on 2016/9/28.
 */
public class WatchListModelSimple implements WatchListModel {
    private WatchListPresenter mPresenter;
    private Activity mActivity;
    private ConnectManager mConnectManager;

    public WatchListModelSimple(WatchListPresenter mPresenter, WatchListActivity activity) {
        this.mPresenter = mPresenter;
        mActivity = activity;
    }

    @Override
    public void getData(int page) {

    }

    @Override
    public void initData() {
        mConnectManager = ConnectManager.getInstance();
    }

    @Override
    public void connect() {


        String address = ShareUtils.getBTAddress();
        if (TextUtils.isEmpty(address)) {
            ToastUtils.show("请先设置蓝牙地址!");
            mPresenter.setBlueToothAddress();

        } else {
            BluetoothDevice device = MyApp.getApp().getBTManager()
                    .initBTAdapter()
                    .getBTAdapter()
                    .getRemoteDevice(address);
            mConnectManager.setUserListener(new ConnectManager.UserConnectListener() {
                @Override
                public void onConnectSuccess() {
                    mPresenter.onConnectSuccess();
                    mPresenter.show("连接成功");
                }

                @Override
                public void onConnecting() {
                    mPresenter.onConnecting();
//                    mPresenter.show("连接中...");

                }

                @Override
                public void onDisconnect() {
                    mPresenter.onDisconnect();
                    mPresenter.show("连接断开");
                }

                @Override
                public void onConnectError() {
                    mPresenter.onConnectError();
                    mPresenter.show("连接错误,请重新连接");

                }

                @Override
                public void onWrite(byte[] bytes) {
                }

                @Override
                public void onRead(byte[] bytes, int len) {
//                    String json = TVDataReceiveUtils.receive(mBTDataTrans, bytes, len);
//                    Log.i(TAG, "onRead: " + json);
//                    if (json != null) {
//                        TVData tvData = new Gson().fromJson(json, TVData.class);
//                        if (tvData.getCode() == TVData.TYPE_RESULT) {
//                            Log.i(TAG, "onRead: resultCode " + tvData.getResult());
//                            mTVDataSendManager.setResultCode(tvData.getResult());
//                        }
//                    }
                }
            });
            mConnectManager.setAsUser(device);


        }
    }

    @Override
    public void shutDownConnect() {
        mConnectManager.close();
    }

    @Override
    public void search(String searchContent) {


        List<WatchBean> list = new ArrayList<>();
        WatchBean watchBean = new WatchBean();
        watchBean.setId(12);
        watchBean.setType(WatchBean.TYPE_CHECK);
//        list.add(watchBean);
        mPresenter.searchResult(list);
    }
}
