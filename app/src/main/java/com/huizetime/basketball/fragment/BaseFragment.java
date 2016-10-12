package com.huizetime.basketball.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huizetime.basketball.activity.WatchLoggingActivity;
import com.huizetime.basketball.presenter.MainPresenterListener;

/**
 * Created by water_fairy on 2016/9/29.
 */

public class BaseFragment extends Fragment {
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
