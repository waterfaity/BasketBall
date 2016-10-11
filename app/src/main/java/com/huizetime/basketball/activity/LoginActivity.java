package com.huizetime.basketball.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.utils.PermissionUtils;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化权限
        PermissionUtils.requestPermission(this, PermissionUtils.REQUEST_LOCATION);
        TextView textView = (TextView) findViewById(R.id.login);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/AkzidenzGrotesk-Bold.otf");
        textView.setTypeface(typeface);
        textView.setText("1234567890");
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.i(TAG, "onCreate:heightPixels "+displayMetrics.heightPixels);
        Log.i(TAG, "onCreate:widthPixels "+displayMetrics.widthPixels);
    }

    public void login(View view) {

        startActivity(new Intent(this, WatchListActivity.class));
        finish();
    }
}
