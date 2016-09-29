package com.huizetime.basketball.utils;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by water_fairy on 2016/9/29.
 */

public class AnimationUtils {
    public static RotateAnimation getRotateAnim() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(500);
        LinearInterpolator linearInterpolator = new LinearInterpolator();
        rotateAnimation.setInterpolator(linearInterpolator);
        return rotateAnimation;
    }

    public static TranslateAnimation getSearchBarAnim(boolean toLeft) {
        float start = 1;
        float end = 0;
        if (!toLeft) {
            start = 0;
            end = 1;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, start, Animation.RELATIVE_TO_SELF, end,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0);

        translateAnimation.setDuration(300);
//        translateAnimation.setFillAfter(true);
        return translateAnimation;
    }
}
