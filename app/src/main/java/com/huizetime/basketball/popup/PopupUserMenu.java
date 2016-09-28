package com.huizetime.basketball.popup;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.huizetime.basketball.R;
import com.huizetime.basketball.popup.base.BasePopupWindow;

/**
 * Created by water_fairy on 2016/9/28.
 */

public class PopupUserMenu extends BasePopupWindow implements View.OnClickListener {
    public static final int MENU_CHANGE_PW = 0;
    public static final int MENU_QUIT = 1;
    private TextView mMenu1, mMenu2;
    private OnUserMenuClickListener clickListener;

    public PopupUserMenu(Activity context) {
        super(context);
        mMenu1 = (TextView) mPopupView.findViewById(R.id.menu_change_pw);
        mMenu2 = (TextView) mPopupView.findViewById(R.id.menu_quit);
        mMenu1.setOnClickListener(this);
        mMenu2.setOnClickListener(this);
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
        return LayoutInflater.from(mContext).inflate(R.layout.menu_user, null);
    }

    @Override
    public View getAnimationView() {
        return mPopupView.findViewById(R.id.popup_anim);
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            if (v.getId() == R.id.menu_change_pw) {
                clickListener.onMenuClick(MENU_CHANGE_PW);
            } else {
                clickListener.onMenuClick(MENU_QUIT);
            }
        }
    }


    public void setOnMenuClickListener(OnUserMenuClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnUserMenuClickListener {
        void onMenuClick(int position);
    }
}
