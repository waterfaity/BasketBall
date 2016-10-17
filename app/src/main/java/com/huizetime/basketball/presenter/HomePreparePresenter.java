package com.huizetime.basketball.presenter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.huizetime.basketball.R;
import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.database.DBUtils;
import com.huizetime.basketball.database.WatchDB;
import com.huizetime.basketball.fragment.prepare.HomePrepareFragment;
import com.huizetime.basketball.listener.OnBelClickListener;
import com.huizetime.basketball.model.HomePrepareModel;
import com.huizetime.basketball.model.HomePrepareModelSimple;
import com.huizetime.basketball.utils.BroadcastUtils;
import com.huizetime.basketball.utils.DialogUtils;
import com.huizetime.basketball.utils.StringUtils;
import com.huizetime.basketball.view.HomePrepareView;

/**
 * Created by water_fairy on 2016/10/13.
 */
public class HomePreparePresenter implements HomePreparePresenterListener {
    private Activity mActivity;
    private HomePrepareModel mModel;
    private HomePrepareView mView;

    public HomePreparePresenter(HomePrepareFragment fragment) {
        this.mActivity = fragment.getActivity();
        mView = fragment;
        mModel = new HomePrepareModelSimple(mActivity, this);
    }

    @Override
    public void startWatch() {
        DialogUtils.showDialog(mActivity, StringUtils.getString(R.string.watch_start),
                StringUtils.getString(R.string.ensure_start_watch), new OnBelClickListener() {
                    @Override
                    public void onBelClick(boolean bel) {
                        mView.startWatch();
                    }
                }).showPopupWindow();
    }

    @Override
    public void changeArea() {
        DialogUtils.showDialog(
                mActivity,
                StringUtils.getString(R.string.change_area),
                StringUtils.getString(R.string.is_change_area),
                new OnBelClickListener() {
                    @Override
                    public void onBelClick(boolean bel) {
                        BroadcastUtils.changeArea();
                    }
                }
        ).showPopupWindow();
    }

    @Override
    public void cancellation() {
        WatchDB watchDB = DBUtils.findOne(WatchDB.class, "watchId", MyApp.getApp().getWatchId() + "");
        String watchName = watchDB.getWatchName();
        DialogUtils.showDialog(
                mActivity,
                StringUtils.getString(R.string.cancellation),
                "请确认[" + watchName + "]是否作废?\n确认后该比赛将结束.",
                new OnBelClickListener() {
                    @Override
                    public void onBelClick(boolean bel) {
                        BroadcastUtils.cancellation();
                    }
                }
        ).showPopupWindow();
    }
}
