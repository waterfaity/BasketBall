package com.huizetime.basketball.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.huizetime.basketball.application.MyApp;

/**
 * Created by water_fairy on 2016/9/26.
 */
public class ToastUtils {
    private static Toast mToast;
    private static Context mContext;

    private static void initToast() {
        if (mContext == null) {
            mContext = MyApp.getApp().getApplicationContext();
        }
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        }
    }

    public static void show(int resId) {
        initToast();
        show(mContext.getResources().getString(resId));
    }

    public static void show(String content) {
        initToast();
        mToast.setText(content);
        show();
    }

    private static void show() {
        mToast.show();
    }
}

