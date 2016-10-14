package com.huizetime.basketball.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by water_fairy on 2016/10/13.
 * 管理阶段模块
 * 如:准备阶段 (0准备首页,1比赛开始,2首发球员,3一方弃权,4球衣修改)
 *
 */

public abstract class BaseFragment extends Fragment {
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

    protected void setFragment(int pos) {
        ((RootBaseFragment) getParentFragment()).setCurrentFragment(pos);
    }
    protected void setBack() {
        ((RootBaseFragment) getParentFragment()).setBack();
    }
}
