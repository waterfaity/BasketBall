package com.huizetime.basketball.fragment;

import android.support.v4.app.Fragment;
import android.view.View;

import com.huizetime.basketball.activity.WatchLoggingActivity;
import com.huizetime.basketball.presenter.MainPresenterListener;

/**
 * Created by water_fairy on 2016/9/29.
 */

public class BaseFragment extends Fragment {
    protected static WatchLoggingActivity mActivity;
    protected static MainPresenterListener mMainPresenter;
    protected View mMainView;

    public static void init(WatchLoggingActivity activity) {
        mActivity = activity;
        mMainPresenter = activity.getPresenter();
    }

}
