package com.huizetime.basketball.fragment.prepare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by water_fairy on 2016/10/13.
 */

public abstract class BasePrepareFragment extends Fragment {
    protected int mResId;
    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(mResId, container, false);
        findView();
        initView();
        initData();
        return mView;
    }

    protected abstract void findView();

    protected abstract void initData();

    protected abstract void initView();
}
