package com.huizetime.basketball.presenter;

import android.app.Activity;

import com.huizetime.basketball.activity.WatchInfoActivity;
import com.huizetime.basketball.view.WatchInfoView;

/**
 * Created by water_fairy on 2016/9/29.
 */
public class WatchInfoPresenter implements WatchInfoPresenterListener {
    private WatchInfoView mView;
    private Activity mActivity;

    public WatchInfoPresenter(WatchInfoActivity activity) {
        mView = activity;
        mActivity = activity;
    }

    @Override
    public void back() {
        mView.back();
    }
}
