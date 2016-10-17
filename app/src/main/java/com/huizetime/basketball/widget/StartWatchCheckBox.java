package com.huizetime.basketball.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.huizetime.basketball.R;

/**
 * Created by water_fairy on 2016/10/17.
 */

public class StartWatchCheckBox extends ImageView implements View.OnClickListener {
    private boolean checked = false;
    private boolean canClick;
    private OnStartWatchCheckBoxClickListener listener;

    public StartWatchCheckBox(Context context) {
        super(context);
    }

    public StartWatchCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundResource(R.drawable.start_watch_no_checked);
        setOnClickListener(this);
    }

    public StartWatchCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

        if (canClick) {

            checked = true;
            setBackgroundResource(R.drawable.start_watch_checked);
        }
        if (listener != null) {
            listener.onStartWatchBoxClick(this);
        }
    }

    public boolean isChecked() {
        return checked;
    }

    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public void setOnStartWatchClickListener(OnStartWatchCheckBoxClickListener listener) {
        this.listener = listener;
    }

    public void reverse() {
        checked = false;
        setBackgroundResource(R.drawable.start_watch_no_checked);
    }

    public interface OnStartWatchCheckBoxClickListener {
        void onStartWatchBoxClick(View view);
    }
}
