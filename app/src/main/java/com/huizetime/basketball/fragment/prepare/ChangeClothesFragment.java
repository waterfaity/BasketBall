package com.huizetime.basketball.fragment.prepare;

import android.view.View;

import com.huizetime.basketball.R;
import com.huizetime.basketball.fragment.base.BaseFragment;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.widget.ClothesView;
import com.huizetime.basketball.widget.MultiClothesView;

/**
 * Created by water_fairy on 2016/10/14.
 */

public class ChangeClothesFragment extends BaseFragment implements View.OnClickListener, MultiClothesView.OnClothesClickListener {
    private MultiClothesView mTeamAClothes, mTeamBClothes;

    {
        mResId = R.layout.fragment_change_clothes;
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
        mTeamAClothes.setOnClothesClickListener(this);
        mTeamBClothes.setOnClothesClickListener(this);
        mTeamAClothes.initMultiClothesView(15,MultiClothesView.WHICH_A);
        mTeamBClothes.initMultiClothesView(15,MultiClothesView.WHICH_B);
        mTeamAClothes.SetCanSelectNum(0);
        mTeamBClothes.SetCanSelectNum(0);
    }

    /**
     * 数据还原,下次使用
     */
    @Override
    public void reverseData() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_int_fragment) {
            setBack();
        }
    }

    @Override
    public void onClothesClick(MultiClothesView view, ClothesView clothesView) {
        if (view.getId() == R.id.team_a) {
            ToastUtils.show("A队" + clothesView.getNum() + "号");
        } else {
            ToastUtils.show("B队" + clothesView.getNum() + "号");
        }
    }
}
