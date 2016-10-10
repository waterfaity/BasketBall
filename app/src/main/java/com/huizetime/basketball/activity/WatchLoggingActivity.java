package com.huizetime.basketball.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.huizetime.basketball.R;
import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.bean.tv.TVData;
import com.huizetime.basketball.fragment.BaseFragment;
import com.huizetime.basketball.manager.BTManager;
import com.huizetime.basketball.manager.TVDataSendManager;
import com.huizetime.basketball.presenter.MainPresenter;
import com.huizetime.basketball.presenter.MainPresenterListener;
import com.huizetime.basketball.utils.ShareUtils;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.utils.ToolbarUtils;
import com.huizetime.basketball.view.MainView;
import com.huizetime.basketball.widget.BigNumView;
import com.huizetime.basketball.widget.CourtView;
import com.huizetime.basketball.widget.NumView;

import java.io.File;

public class WatchLoggingActivity extends AppCompatActivity implements MainView, CourtView.OnPointClickListener {

    private MainPresenterListener mPresenter;
    private static final String TAG = "main";
    private int[] numRes = new int[]{};

    private CourtView mCourtView;//球场
    private BigNumView mBNScore1, mBNScore2;//得分
    private int mWidth;//屏幕宽

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
        ToolbarUtils.init(this, R.string.watch_log, mPresenter);
    }

    private void initData() {

        mPresenter = new MainPresenter(this);
        mPresenter.initData();
        BaseFragment.init(this);
    }

    private void initView() {
        //初始化比分
        mBNScore1.setItemNum(false, 3, 5);
        //设置球场  比例: 15/28
        mCourtView.getLayoutParams().height = (int) (mWidth * 15 / (float) 28);
        mCourtView.setOnPointListener(this);
        mCourtView.setWidth(28f);
    }

    private void findView() {
        mBNScore1 = (BigNumView) findViewById(R.id.a_score);
        mBNScore2 = (BigNumView) findViewById(R.id.b_score);
        mCourtView = (CourtView) findViewById(R.id.court_view);
    }

    private void setData() {
        int tvPage = MyApp.getApp().getTVCurrentPage();

        switch (tvPage) {
            case MyApp.PAGE_CONNECT:
                //未录入 未签到

                break;
            case MyApp.PAGE_SIGN:
                //签到

                break;
            case MyApp.PAGE_EVENT:
                //录入

                break;
        }
    }


    public void onClick(View view) {
    }


    @Override
    public void onPointClick(String left, String right, int score) {
        String content = "left:" + left + " right" + right + " score:" + score;
        ToastUtils.show(content);
        Log.i(TAG, "onPointClick: " + content);
        mBNScore1.setNum(score*111, true);
    }

    @Override
    public void back() {

        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mPresenter.back();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    public MainPresenterListener getPresenter() {
        return mPresenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, Menu.FIRST, 1, R.string.sign)
                .setIcon(R.drawable.ic_account_box_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, Menu.FIRST + 1, 2, R.string.watch_info)
                .setIcon(R.drawable.ic_backup_black_24dp)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == Menu.FIRST) {
            toSign();
        } else {
            toWatchInfo();
        }
        return false;
    }

    private void toWatchInfo() {
        Intent intent = new Intent(this, WatchInfoActivity.class);
        startActivity(intent);
    }

    private void toSign() {
        Intent intent = new Intent(this, SignActivity.class);
        startActivity(intent);
    }
}
