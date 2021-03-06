package com.huizetime.basketball.fragment.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huizetime.basketball.R;
import com.huizetime.basketball.activity.WatchLoggingActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2016/10/13.
 * 管理4大模块
 * 1准备阶段
 * 2进行阶段
 * 3队伍事件
 * 4结束阶段
 */

public abstract class RootBaseFragment extends Fragment {
    protected View mView;
    protected List<Fragment> mFragmentList;
    protected int mLastFragment;
    protected int mUpFragment;


    public static WatchLoggingActivity mActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_root, container, false);
        mFragmentList = new ArrayList<>();
        initFragment();
        return mView;
    }


    protected void initFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        if (mFragmentList == null || mFragmentList.size() < 1) {
            return;
        }
        for (int i = 0; i < mFragmentList.size(); i++) {
            if (i > 0) {
                Fragment fragment = mFragmentList.get(i);
                fragmentTransaction
                        .add(R.id.frameLayout, fragment)
                        .detach(fragment);
            }
        }
        Fragment homeFragment = mFragmentList.get(0);
        fragmentTransaction.add(R.id.frameLayout, homeFragment)
                .attach(homeFragment).commit();

    }

    public void setCurrentFragment(int pos) {
        //指定显示fragment
        if (mLastFragment == pos) {
            return;
        } else {
            getChildFragmentManager().beginTransaction()
                    .detach(mFragmentList.get(mLastFragment))
                    .attach(mFragmentList.get(pos))
                    .commit();
        }
        isBack = false;
        mUpFragment = mLastFragment;
        mLastFragment = pos;
    }

    protected void setBack() {
        //只允许返回一次
        if (mUpFragment == mLastFragment) return;
//        mLastFragment = mUpFragment;
//        isBack = true;
        setCurrentFragment(mUpFragment);
    }

    private boolean isBack;

}
