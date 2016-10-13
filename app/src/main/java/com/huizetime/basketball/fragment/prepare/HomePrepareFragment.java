package com.huizetime.basketball.fragment.prepare;
/**
 * 比赛准备阶段
 */

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.fragment.RootBaseFragment;
import com.huizetime.basketball.presenter.HomePreparePresenter;
import com.huizetime.basketball.presenter.HomePreparePresenterListener;
import com.huizetime.basketball.view.HomePrepareView;
import com.huizetime.basketball.widget.MultiClothesView;


public class HomePrepareFragment extends BasePrepareFragment implements HomePrepareView, View.OnClickListener {
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
                RootBaseFragment parentFragment = (RootBaseFragment) getParentFragment();
                parentFragment.setCurrentFragment(1);
                break;
            case R.id.change_area:
                //场地交换
                break;
            case R.id.give_up:
                //一方弃权
                break;
            case R.id.cancellation:
                //比赛作废
                break;
        }
    }

    @Override
    protected void initData() {
        mPresenter = new HomePreparePresenter(getActivity());
    }

    @Override
    protected void initView() {

    }
}
