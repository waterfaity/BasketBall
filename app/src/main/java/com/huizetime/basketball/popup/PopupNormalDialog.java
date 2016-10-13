package com.huizetime.basketball.popup;

import android.app.Activity;
import android.graphics.drawable.RippleDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.listener.OnBelClickListener;
import com.huizetime.basketball.popup.base.BasePopupWindow;

/**
 * Created by water_fairy on 2016/10/13.
 */
public class PopupNormalDialog extends BasePopupWindow implements View.OnClickListener {
    private TextView mTitle;
    private TextView mContent;

    public PopupNormalDialog(Activity context) {
        super(context);
        mTitle = (TextView) findViewById(R.id.dialog_title);
        mContent = (TextView) findViewById(R.id.dialog_content);
        findViewById(R.id.dialog_cancel).setOnClickListener(this);
        findViewById(R.id.dialog_ensure).setOnClickListener(this);
    }

    public PopupNormalDialog(Activity context, String title, String content) {
        this(context);
        setContent(title, content);
    }

    public PopupNormalDialog(Activity context, String title, String content, OnBelClickListener clickListener) {
        this(context, title, content);
        setOnClickListener(clickListener);
    }

    /**
     * PopupWindow展示出来后，需要执行动画的View.一般为蒙层之上的View
     *
     * @return
     */
    @Override
    protected Animation getShowAnimation() {
        return getDefaultAlphaAnimation();
    }

    @Override
    public Animation getExitAnimation() {
        return getDefaultExitAlphaAnimation();
    }

    /**
     * 设置一个点击后触发dismiss PopupWindow的View，一般为蒙层
     *
     * @return
     */
    @Override
    protected View getClickToDismissView() {
        return null;
    }

    @Override
    public View getPopupView() {
        return LayoutInflater.from(mContext).inflate(R.layout.dialog_normal, null);
    }

    @Override
    public View getAnimationView() {
        return mPopupView.findViewById(R.id.popup_anim);
    }

    public void setContent(String title, String content) {
        mTitle.setText(title);
        mContent.setText(content);
    }

    private OnBelClickListener onClickListener;

    public void setOnClickListener(OnBelClickListener clickListener) {
        this.onClickListener = clickListener;

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_cancel) {
            if (onClickListener != null) {
                onClickListener.onBelClick(false);
            }
        } else {
            if (onClickListener != null) {
                onClickListener.onBelClick(true);
            }
        }
        dismiss();
    }
}
