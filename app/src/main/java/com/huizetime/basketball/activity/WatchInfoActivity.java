package com.huizetime.basketball.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huizetime.basketball.R;
import com.huizetime.basketball.presenter.WatchInfoPresenter;
import com.huizetime.basketball.presenter.WatchInfoPresenterListener;
import com.huizetime.basketball.utils.ToolbarUtils;
import com.huizetime.basketball.view.WatchInfoView;

public class WatchInfoActivity extends AppCompatActivity implements WatchInfoView {
    private WatchInfoPresenterListener mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_info);
        initData();
        ToolbarUtils.init(this, R.string.watch_info, mPresenter);
    }

    private void initData() {
        mPresenter = new WatchInfoPresenter(this);
    }

    @Override
    public void back() {
        finish();
    }
}
