package com.huizetime.basketball.fragment.root;

import com.huizetime.basketball.fragment.base.RootBaseFragment;
import com.huizetime.basketball.fragment.prepare.ChangeClothesFragment;
import com.huizetime.basketball.fragment.prepare.FirstPlayerFragment;
import com.huizetime.basketball.fragment.prepare.GiveUpFragment;
import com.huizetime.basketball.fragment.prepare.HomePrepareFragment;
import com.huizetime.basketball.fragment.prepare.WatchStartFragment;

public class RootPrepareFragment extends RootBaseFragment {

    public static final int PREPARE_HOME = 0;//准备阶段主页
    public static final int PREPARE_START_WATCH = 1;//开始比赛
    public static final int PREPARE_FIRST_PLAYER = 2;//首发球员
    public static final int PREPARE_GIVE_UP = 3;//一方弃权
    public static final int PREPARE_CHANGE_CLOTHES = 4;//衣服修改

    private HomePrepareFragment mHomePrepareFragment;
    private WatchStartFragment mWatchStartFragment;
    private FirstPlayerFragment mFirstPlayerFragment;
    private GiveUpFragment mGiveUpFragment;
    private ChangeClothesFragment mChangeClothesFragment;


    @Override
    public void initFragment() {

        mHomePrepareFragment = new HomePrepareFragment();
        mWatchStartFragment = new WatchStartFragment();
        mFirstPlayerFragment = new FirstPlayerFragment();
        mGiveUpFragment = new GiveUpFragment();
        mChangeClothesFragment = new ChangeClothesFragment();

        mFragmentList.add(mHomePrepareFragment);
        mFragmentList.add(mWatchStartFragment);
        mFragmentList.add(mFirstPlayerFragment);
        mFragmentList.add(mGiveUpFragment);
        mFragmentList.add(mChangeClothesFragment);
        super.initFragment();

    }

}
