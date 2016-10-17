package com.huizetime.basketball.fragment.prepare;
/**
 * 比赛准备阶段
 */

import android.view.View;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.fragment.base.BaseFragment;
import com.huizetime.basketball.fragment.root.RootPrepareFragment;
import com.huizetime.basketball.listener.OnBelClickListener;
import com.huizetime.basketball.presenter.HomePreparePresenter;
import com.huizetime.basketball.presenter.HomePreparePresenterListener;
import com.huizetime.basketball.utils.DialogUtils;
import com.huizetime.basketball.utils.StringUtils;
import com.huizetime.basketball.view.HomePrepareView;
import com.huizetime.basketball.widget.MultiClothesView;


public class HomePrepareFragment extends BaseFragment implements HomePrepareView, View.OnClickListener {
    private HomePreparePresenterListener mPresenter;

    {
        mResId = R.layout.fragment_home_prepare;
    }

    private MultiClothesView mTeamA;
    private MultiClothesView mTeamB;
    private TextView mWatchStart;
    private TextView mFirstPlayers;
    private TextView mChangeArea;
    private TextView mGiveUp;
    private TextView mCancellation;


    @Override
    protected void findView() {
        mTeamA = (MultiClothesView) mView.findViewById(R.id.team_a);
        mTeamB = (MultiClothesView) mView.findViewById(R.id.team_b);
        mWatchStart = (TextView) mView.findViewById(R.id.watch_start);
        mFirstPlayers = (TextView) mView.findViewById(R.id.first_players);
        mChangeArea = (TextView) mView.findViewById(R.id.change_area);
        mGiveUp = (TextView) mView.findViewById(R.id.give_up);
        mCancellation = (TextView) mView.findViewById(R.id.cancellation);
        mWatchStart.setOnClickListener(this);
        mFirstPlayers.setOnClickListener(this);
        mChangeArea.setOnClickListener(this);
        mGiveUp.setOnClickListener(this);
        mCancellation.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.watch_start:
                //比赛开始
                mPresenter.startWatch();
                break;
            case R.id.first_players:
                //首发球员
                setFragment(RootPrepareFragment.PREPARE_FIRST_PLAYER);
                break;
            case R.id.change_area:
                //场地交换
                mPresenter.changeArea();
                break;
            case R.id.give_up:
                //一方弃权
                setFragment(RootPrepareFragment.PREPARE_GIVE_UP);
                break;
            case R.id.cancellation:
                //比赛作废
                mPresenter.cancellation();
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter = new HomePreparePresenter(this);
    }

    @Override
    protected void initView() {

    }

    /**
     * 数据还原,下次使用
     */
    @Override
    public void reverseData() {

    }

    @Override
    public void startWatch() {
        setFragment(RootPrepareFragment.PREPARE_START_WATCH);
    }
}
