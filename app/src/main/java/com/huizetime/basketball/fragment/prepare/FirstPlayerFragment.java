package com.huizetime.basketball.fragment.prepare;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.huizetime.basketball.R;
import com.huizetime.basketball.fragment.RootBaseFragment;

/**
 * Created by water_fairy on 2016/10/13.
 */

public class FirstPlayerFragment extends BasePrepareFragment implements View.OnClickListener {

    {
        mResId = R.layout.fragment_first_player;
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mView.findViewById(R.id.back_int_fragment).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        ((RootBaseFragment)getParentFragment()).setCurrentFragment(1);
    }
}
