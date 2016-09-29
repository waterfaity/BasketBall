package com.huizetime.basketball.presenter;

import android.app.Activity;

import com.huizetime.basketball.activity.ChangePasswordActivity;
import com.huizetime.basketball.view.ChangePasswordView;

/**
 * Created by water_fairy on 2016/9/29.
 */
public class ChangePWPresenter implements ChangePWPresenterListener {
    private ChangePasswordView mView;
    private Activity mActivity;

    public ChangePWPresenter(ChangePasswordActivity activity) {
        mView = activity;
        mActivity = activity;
    }

    @Override
    public void back() {
        mView.back();
    }
}
