package com.huizetime.basketball.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import com.huizetime.basketball.R;
import com.huizetime.basketball.listener.OnBelClickListener;
import com.huizetime.basketball.popup.PopupLoading;

/**
 * Created by water_fairy on 2016/9/29.
 */
public class DialogUtils {
    public static void show2(Activity activity, String content, final OnBelClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("提示");
        alertDialog.setMessage(content);
        alertDialog.setButton(DialogInterface.BUTTON1, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {

                    listener.onBelClick(true);
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON2, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {

                    listener.onBelClick(false);
                }
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void show1(Activity activity, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setTitle("提示");
        alertDialog.setMessage(content);
        alertDialog.setButton(DialogInterface.BUTTON1, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static PopupLoading loading(Activity activity) {

        PopupLoading loading = new PopupLoading(activity);
        loading.showPopupWindow();
        return loading;
    }

}
