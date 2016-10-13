package com.huizetime.basketball.presenter;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.huizetime.basketball.R;
import com.huizetime.basketball.listener.OnBelClickListener;
import com.huizetime.basketball.model.HomePrepareModel;
import com.huizetime.basketball.model.HomePrepareModelSimple;
import com.huizetime.basketball.utils.DialogUtils;
import com.huizetime.basketball.utils.StringUtils;

/**
 * Created by water_fairy on 2016/10/13.
 */
public class HomePreparePresenter implements HomePreparePresenterListener {
    private Activity mActivity;
    private HomePrepareModel mModel;

    public HomePreparePresenter(FragmentActivity activity) {
        this.mActivity = activity;
        mModel = new HomePrepareModelSimple(this);
    }

    @Override
    public void startWatch() {
        DialogUtils.showDialog(mActivity, StringUtils.getString(R.string.watch_start),
                StringUtils.getString(R.string.ensure_start_watch), new OnBelClickListener() {
                    @Override
                    public void onBelClick(boolean bel) {

                    }
                }).showPopupWindow();
    }
}
