package com.huizetime.basketball.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huizetime.basketball.R;
import com.huizetime.basketball.presenter.ChangePWPresenter;
import com.huizetime.basketball.presenter.ChangePWPresenterListener;
import com.huizetime.basketball.utils.ToolbarUtils;
import com.huizetime.basketball.view.ChangePasswordView;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePasswordView {
    private ChangePWPresenterListener mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        initData();
        ToolbarUtils.init(this, R.string.change_pw, mPresenter);
    }

    private void initData() {
        mPresenter = new ChangePWPresenter(this);
    }

    @Override
    public void back() {
        finish();
    }
}
