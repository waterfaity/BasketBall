package com.huizetime.basketball.fragment.prepare;
/**
 * 比赛准备阶段
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.widget.MultiClothesView;


public class HomePrepareFragment extends Fragment {
    private View mView;
    private MultiClothesView mTeamA;
    private MultiClothesView mTeamB;
    private TextView mWatchStart;
    private TextView mFirstPlayers;
    private TextView mChangeArea;
    private TextView mGiveUp;
    private TextView mCancellation;

    private void assignViews() {
        mTeamA = (MultiClothesView) mView.findViewById(R.id.team_a);
        mTeamB = (MultiClothesView) mView.findViewById(R.id.team_b);
        mWatchStart = (TextView) mView.findViewById(R.id.watch_start);
        mFirstPlayers = (TextView) mView.findViewById(R.id.first_players);
        mChangeArea = (TextView) mView.findViewById(R.id.change_area);
        mGiveUp = (TextView) mView.findViewById(R.id.give_up);
        mCancellation = (TextView) mView.findViewById(R.id.cancellation);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_prepare, container, false);
        findView();
        initView();
        initData();
        return mView;
    }

    private void initData() {

    }

    private void initView() {

    }

    private void findView() {
        assignViews();
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.watch_start:
                //比赛开始
                break;
            case R.id.first_players:
                //首发球员
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
}
