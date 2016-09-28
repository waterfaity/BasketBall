package com.huizetime.basketball.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.huizetime.basketball.R;
import com.huizetime.basketball.utils.PermissionUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化权限
        PermissionUtils.requestPermission(this, PermissionUtils.REQUEST_LOCATION);
    }

    public void login(View view) {
        startActivity(new Intent(this, WatchListActivity.class));
        finish();
    }
}
