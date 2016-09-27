package com.huizetime.basketball.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.huizetime.basketball.R;
import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.manager.BTManager;
import com.huizetime.basketball.manager.TVDataSendManager;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.presenter.MainPresenterListener;
import com.huizetime.basketball.utils.ShareUtils;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.view.MainView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenterListener mPresenter;
    private static final String TAG = "main";
    private BTManager mBTManager;
    private String mAddress = "24:0A:64:6F:E0:BE";//一体机
    private TVDataSendManager mTVDataSendManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        findView();
        initView();
        setData();
    }

    private void initData() {
        ShareUtils.getSetting().edit().putString("address", mAddress).apply();
        mBTManager = BTManager.getInstance();
        mPresenter = new MainPresenter(this);
        mPresenter.initData();
    }

    private void initView() {

    }

    private void findView() {

    }

    private void setData() {

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_watch_info:
                mPresenter.setWatchInfo();
                break;
            case R.id.send_sign:
                mPresenter.sendSign();
                break;
            case R.id.send_score_info:
                mPresenter.sendScore();
                break;
            case R.id.send_chang:
                mPresenter.change();
                break;
            case R.id.send_img:
                File file = new File("/sdcard/jj.jpg");
                if (file.exists()) {
                    mPresenter.sendImg(TVData.TYPE_QR_CODE, "/sdcard/jj.jpg");
                } else {
                    ToastUtils.show("图片未找到");
                }
                break;
        }
    }

    public void close(View view) {

        if (mBTManager != null && mBTManager.isUserConnected()) {
            mPresenter.close();
        } else {
            Log.i(TAG, "close: 已断开");
        }


    }

    public void connect(View view) {


        if (mBTManager != null && mBTManager.isUserConnected()) {
            Log.i(TAG, "close: 已连接,无需重置");
        } else {
            mPresenter.connect();
        }
    }


}
