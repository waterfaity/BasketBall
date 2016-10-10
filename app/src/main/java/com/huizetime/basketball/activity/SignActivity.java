package com.huizetime.basketball.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huizetime.basketball.R;
import com.huizetime.basketball.application.MyApp;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        findView();
        initView();
        initData();
    }

    private void initView() {

    }

    private void findView() {

    }

    private void initData() {
        MyApp.getApp().setTVCurrentPage(MyApp.PAGE_SIGN);
    }
}
