package com.huizetime.basketball.popup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.popup.base.BasePopupWindow;
import com.huizetime.basketball.utils.AnimationUtils;

/**
 * Created by water_fairy on 2016/9/29.
 */

public class PopupLoading extends BasePopupWindow {
    private ImageView imageView;

    public PopupLoading(Activity context) {
        super(context);
        imageView = (ImageView) findViewById(R.id.loading);
        imageView.startAnimation(AnimationUtils.getRotateAnim());
    }

    @Override
    protected Animation getShowAnimation() {
        return getDefaultAlphaAnimation();
    }

    @Override
    protected View getClickToDismissView() {
        return mPopupView;
    }

    @Override
    public View getPopupView() {
        return LayoutInflater.from(mContext).inflate(R.layout.loading, null);
    }

    @Override
    public View getAnimationView() {
        return mPopupView.findViewById(R.id.popup_anim);
    }

    public void stopLoading() {
        imageView.clearAnimation();
        dismiss();
    }
}
