package com.huizetime.basketball.fragment.prepare;

import android.support.v4.app.Fragment;

import com.huizetime.basketball.fragment.RootBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class RootPrepareFragment extends RootBaseFragment {
    private HomePrepareFragment mHomePrepareFragment;
    private WatchStartFragment mWatchStartFragment;
    private FirstPlayerFragment mFirstPlayerFragment;
    private GiveUpFragment mGiveUpFragment;
    private int mUpFragment;
    private int mLastFragment;


    @Override
    public void initFragment() {

        mHomePrepareFragment = new HomePrepareFragment();
        mWatchStartFragment = new WatchStartFragment();
        mFirstPlayerFragment = new FirstPlayerFragment();
        mGiveUpFragment = new GiveUpFragment();

        mFragmentList.add(mHomePrepareFragment);
        mFragmentList.add(mWatchStartFragment);
        mFragmentList.add(mFirstPlayerFragment);
        mFragmentList.add(mGiveUpFragment);
        super.initFragment();

    }

    @Override
    public void initData() {

    }

}
