package com.huizetime.basketball.model;

import android.app.Activity;
import android.content.Intent;

import com.huizetime.basketball.application.MyApp;
import com.huizetime.basketball.presenter.HomePreparePresenter;
import com.huizetime.basketball.presenter.HomePreparePresenterListener;
import com.huizetime.basketball.utils.BroadcastUtils;
import com.huizetime.basketball.utils.ConstantUtils;

/**
 * Created by water_fairy on 2016/10/13.
 */
public class HomePrepareModelSimple implements HomePrepareModel {
    private HomePreparePresenterListener mListener;
    private Activity mActivity;

    public HomePrepareModelSimple(Activity mActivity, HomePreparePresenter homePreparePresenter) {
        mListener = homePreparePresenter;
        this.mActivity = mActivity;
    }
}


