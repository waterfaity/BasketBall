package com.huizetime.basketball.fragment.prepare;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.activity.WatchListActivity;
import com.huizetime.basketball.activity.WatchLoggingActivity;
import com.huizetime.basketball.fragment.base.BaseFragment;
import com.huizetime.basketball.fragment.root.RootPrepareFragment;
import com.huizetime.basketball.utils.BroadcastUtils;
import com.huizetime.basketball.utils.ConstantUtils;
import com.huizetime.basketball.utils.StringUtils;
import com.huizetime.basketball.utils.ToastUtils;
import com.huizetime.basketball.widget.ClothesView;
import com.huizetime.basketball.widget.MultiClothesView;
import com.huizetime.basketball.widget.StartWatchCheckBox;

/**
 * Created by water_fairy on 2016/10/13.
 */

public class WatchStartFragment extends BaseFragment implements StartWatchCheckBox.OnStartWatchCheckBoxClickListener, MultiClothesView.OnClothesClickListener, View.OnClickListener {
    private StartWatchCheckBox mStartWatch;
    private TextView mATeamName, mBTeamName;
    private RelativeLayout mAGetBall, mBGetBall;
    private MultiClothesView mATeam, mBTeam;
    private boolean isAChecked, isBChecked;
    private int mANum, mBNum;

    {
        mResId = R.layout.fragment_watch_start;
    }

    @Override
    protected void findView() {
        mStartWatch = (StartWatchCheckBox) mView.findViewById(R.id.start_watch_button);
        mATeamName = (TextView) mView.findViewById(R.id.a_team_name);
        mBTeamName = (TextView) mView.findViewById(R.id.b_team_name);
        mAGetBall = (RelativeLayout) mView.findViewById(R.id.a_get_ball);
        mBGetBall = (RelativeLayout) mView.findViewById(R.id.b_get_ball);
        mATeam = (MultiClothesView) mView.findViewById(R.id.team_a);
        mBTeam = (MultiClothesView) mView.findViewById(R.id.team_b);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mATeam.initMultiClothesView(5, MultiClothesView.WHICH_A);
        mBTeam.initMultiClothesView(5, MultiClothesView.WHICH_B);
        mATeam.SetCanSelectNum(1);
        mBTeam.SetCanSelectNum(1);
        mStartWatch.setOnStartWatchClickListener(this);
        mATeam.setOnClothesClickListener(this);
        mBTeam.setOnClothesClickListener(this);

    }

    @Override
    public void reverseData() {
        //数据还原,下次使用
        isBChecked = false;
        isAChecked = false;
        mStartWatch.reverse();
        mAGetBall.setClickable(false);
        mBGetBall.setClickable(false);

    }

    @Override
    public void onStartWatchBoxClick(View view) {
        if (!isAChecked || !isBChecked) {
            ToastUtils.show(StringUtils.getString(R.string.check_palyer));
        }
        if (mStartWatch.isChecked()) {
            //选中抢球按钮 改变背景
            mAGetBall.setOnClickListener(this);
            mBGetBall.setOnClickListener(this);
        }

    }

    @Override
    public void onClothesClick(MultiClothesView multiClothesView, ClothesView clothesView) {
        if (multiClothesView.getId() == R.id.team_a) {
            if (clothesView.isSelect()) {
                isAChecked = true;
                mANum = clothesView.getNum();
            } else {
                isAChecked = false;
                mANum = 0;
            }
        } else {
            if (clothesView.isSelect()) {
                isBChecked = true;
                mBNum = clothesView.getNum();
            } else {
                isBChecked = false;
                mBNum = clothesView.getNum();
            }

        }
        if (isAChecked && isBChecked) {
            mStartWatch.setCanClick(true);
        } else {
            mStartWatch.setCanClick(false);
        }
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (!isAChecked || !isBChecked) {
            ToastUtils.show(StringUtils.getString(R.string.check_palyer));
            return;
        }
        if (v.getId() == R.id.a_get_ball) {
            BroadcastUtils.getBall(ConstantUtils.LEFT,mANum);
        } else {
            BroadcastUtils.getBall(ConstantUtils.RIGHT,mBNum);
        }
        setFragment(RootPrepareFragment.PREPARE_HOME);
        reverseData();
        BroadcastUtils.setFragment(WatchLoggingActivity.FRAGMENT_PROCESS);
    }


}
