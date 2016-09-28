package com.huizetime.basketball.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.huizetime.basketball.R;
import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.manager.BTManager;
import com.huizetime.basketball.manager.TVDataSendManager;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.presenter.MainPresenterListener;
import com.huizetime.basketball.utils.ShareUtils;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.view.MainView;
import com.huizetime.basketball.widget.BigNumView;
import com.huizetime.basketball.widget.CourtView;
import com.huizetime.basketball.widget.NumView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements MainView, CourtView.OnPointClickListener {

    private MainPresenterListener mPresenter;
    private static final String TAG = "main";
    private BTManager mBTManager;
    private String mAddress = "24:0A:64:6F:E0:BE";//一体机
    private TVDataSendManager mTVDataSendManager;
    private CourtView mCourtView;
    private NumView mNumView;
    private BigNumView mBigNumView;
    private int mWidth;
    private int num, bigNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mWidth = displayMetrics.widthPixels;
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
        mCourtView.getLayoutParams().height = (int) (mWidth * 15 / (float) 28);
        mCourtView.setOnPointListener(this);
        mCourtView.setWidth(28f);
        mNumView.setResIds(new int[]{
                R.mipmap.num0, R.mipmap.num1, R.mipmap.num2, R.mipmap.num3, R.mipmap.num4,
                R.mipmap.num5, R.mipmap.num6, R.mipmap.num7, R.mipmap.num8, R.mipmap.num9});
        mBigNumView.setResId(new int[]{
                R.mipmap.num0, R.mipmap.num1, R.mipmap.num2, R.mipmap.num3, R.mipmap.num4,
                R.mipmap.num5, R.mipmap.num6, R.mipmap.num7, R.mipmap.num8, R.mipmap.num9});
        mBigNumView.setItemNum(3, 10);
    }

    private void findView() {
        mCourtView = (CourtView) findViewById(R.id.court_view);
        mNumView = (NumView) findViewById(R.id.num_view);
        mBigNumView = (BigNumView) findViewById(R.id.big_num_view);
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


    @Override
    public void onPointClick(String left, String right, int score) {
        String content = "left:" + left + " right" + right + " score:" + score;
        ToastUtils.show(content);
        Log.i(TAG, "onPointClick: " + content);
        num++;
        bigNum += 60;
        if (num == 10) {
            num = 0;
        }

        mNumView.setNum(num, true);

        mBigNumView.setNum(bigNum, true);

    }
}
