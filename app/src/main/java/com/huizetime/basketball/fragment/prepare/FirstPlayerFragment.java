package com.huizetime.basketball.fragment.prepare;

import android.view.View;

import com.huizetime.basketball.R;
import com.huizetime.basketball.fragment.base.BaseFragment;
import com.huizetime.basketball.fragment.root.RootPrepareFragment;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.widget.MultiClothesView;

/**
 * Created by water_fairy on 2016/10/13.
 */

public class FirstPlayerFragment extends BaseFragment implements View.OnClickListener {

    private MultiClothesView mTeamAClothes, mTeamBClothes;


    {
        mResId = R.layout.fragment_first_player;
    }

    @Override
    protected void findView() {
        mTeamAClothes = (MultiClothesView) mView.findViewById(R.id.team_a);
        mTeamBClothes = (MultiClothesView) mView.findViewById(R.id.team_b);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mView.findViewById(R.id.back_int_fragment).setOnClickListener(this);
        mView.findViewById(R.id.go_to_court).setOnClickListener(this);
        mView.findViewById(R.id.change_clothes).setOnClickListener(this);
        mTeamAClothes.initMultiClothesView(15,MultiClothesView.WHICH_A);
        mTeamBClothes.initMultiClothesView(15,MultiClothesView.WHICH_B);
        mTeamAClothes.SetCanSelectNum(5);
        mTeamBClothes.SetCanSelectNum(5);
    }

    /**
     * 数据还原,下次使用
     */
    @Override
    public void reverseData() {

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_int_fragment:
               setFragment(RootPrepareFragment.PREPARE_HOME);
                break;
            case R.id.change_clothes:
                setFragment(RootPrepareFragment.PREPARE_CHANGE_CLOTHES);
                break;
            case R.id.go_to_court:
                ToastUtils.show("上场");
                break;

        }
    }
}
