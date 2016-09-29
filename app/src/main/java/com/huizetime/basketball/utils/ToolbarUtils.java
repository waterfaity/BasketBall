package com.huizetime.basketball.utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.presenter.BasePresenter;

/**
 * Created by water_fairy on 2016/9/28.
 */

public class ToolbarUtils {
    public static Toolbar init(Context context, int titleId, final BasePresenter presenter) {
        return init(context, titleId, -1, presenter, null);

    }

    public static Toolbar init(Context context,
                               int titleId,
                               int button1,
                               final BasePresenter presenter,
                               final View.OnClickListener clickListener) {
        AppCompatActivity compatActivity = (AppCompatActivity) context;
        Toolbar toolbar = (Toolbar) compatActivity.findViewById(R.id.toolbar);
        TextView title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        title.setText(context.getResources().getText(titleId));
        toolbar.setTitle("");
        compatActivity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.back();
            }
        });
        return toolbar;
    }
}
