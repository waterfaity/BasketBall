package com.huizetime.basketball.fragment.root;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huizetime.basketball.R;
import com.huizetime.basketball.fragment.base.RootBaseFragment;
import com.huizetime.basketball.fragment.process.BatProcessFragment;
import com.huizetime.basketball.fragment.process.FoulProcessFragment;
import com.huizetime.basketball.fragment.process.HomeProcessFragment;
import com.huizetime.basketball.fragment.process.StealProcessFragment;
import com.huizetime.basketball.fragment.process.ViolationProcessFragment;

public class RootProcessFragment extends RootBaseFragment {

    public static final int PROCESS_HOME = 0;//主页
    public static final int PROCESS_BAT = 1;//投球
    public static final int PROCESS_STEAL = 2;//抢断
    public static final int PROCESS_foul = 3;//犯规
    public static final int PROCESS_VIOLATION = 4;//违例


    private HomeProcessFragment homeProcessFragment;
    private BatProcessFragment batProcessFragment;
    private StealProcessFragment stealProcessFragment;
    private FoulProcessFragment foulProcessFragment;
    private ViolationProcessFragment violationProcessFragment;

    @Override
    protected void initFragment() {
        mFragmentList.add(homeProcessFragment);
        mFragmentList.add(batProcessFragment);
        mFragmentList.add(stealProcessFragment);
        mFragmentList.add(foulProcessFragment);
        mFragmentList.add(violationProcessFragment);
        super.initFragment();
    }
}
