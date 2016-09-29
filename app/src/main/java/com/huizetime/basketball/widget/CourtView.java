package com.huizetime.basketball.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huizetime.basketball.utils.MathUtils;

/**
 * Created by water_fairy on 2016/9/27.
 * 坐标
 * 篮球场地大小  28*15
 * 中心 :(14 ,7.5)
 * 球篮 :(1.2,7.5)
 * 罚球 :(5.8,7.5);(7.6,7.5)
 * 半径 :6.25
 */
public class CourtView extends RelativeLayout {


    private static final String TAG = "CourtView";
    private float x;
    private float y;
    private int score;
    private float realWidth;

    //比例   view宽/实际宽
    private float realRadio = 1;
    //比例   view宽/28
    private float radio2 = 1;

    private double z1, z2;//距离球篮距离

    private float width;//view 宽
    private float height;//view 高

    private TextView point;

    private OnPointClickListener listener;


    public CourtView(Context context) {
        super(context);
    }

    public CourtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        point = new TextView(context);
        addView(point);
        ViewGroup.LayoutParams layoutParams = point.getLayoutParams();
        layoutParams.width = 1;
        layoutParams.height = 1;
        point.setBackgroundColor(Color.parseColor("#ff0000"));

    }

    public CourtView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            //按下
            x = event.getX();
            y = event.getY();
            trans();
            Log.i(TAG, "onTouchEvent: " + x + "--" + y);
            handleData();

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            //抬起
            if (listener != null) {
                listener.onPointClick(
                        MathUtils.getPoint2(Math.sqrt(z1) / realRadio),
                        MathUtils.getPoint2(Math.sqrt(z2) / realRadio),
                        score);
            }

            return false;
        }

        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        radio2 = width / (float) 28;
        realRadio = width / realWidth;
        Log.i(TAG, "篮球场地 : width:" + width + " -- height" + height);
    }

    private void handleData() {
        // (x－a)²+(y－b)²=r²
        // 球篮 :(1.2,7.5)
        // 得到公式 左:(x－1.2*radio)²+(y－7.5*radio)²=(6.25*radio)²
        // 得到公式 右:(x－(28-1.2)*radio)²+(y－7.5*radio)²=(6.25*radio)²
        z1 = Math.pow(x - 1.2 * radio2, 2) + Math.pow(y - 7.5 * radio2, 2);
        z2 = Math.pow(x - (28 - 1.2) * radio2, 2) + Math.pow(y - 7.5 * radio2, 2);
        if (z1 < Math.pow(6.25 * radio2, 2) || z2 < Math.pow(6.25 * radio2, 2)) {
            score = 2;
        } else {
            score = 3;
        }
    }

    public void setOnPointListener(OnPointClickListener listener) {
        this.listener = listener;
    }

    public interface OnPointClickListener {
        void onPointClick(String left, String right, int score);
    }

    /**
     * 实际宽度(米/英尺)
     *
     * @param width
     */
    public void setWidth(float width) {
        realWidth = width;
    }

    private int lastX, lastY;

    private void trans() {
        TranslateAnimation translateAnimation = new TranslateAnimation(lastX, x, lastY, y);
        translateAnimation.setFillAfter(true);
        lastX = (int) x;
        lastY = (int) y;
        point.startAnimation(translateAnimation);
    }

}
