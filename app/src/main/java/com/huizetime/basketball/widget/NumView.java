package com.huizetime.basketball.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by water_fairy on 2016/9/27.
 */
public class NumView extends ScrollView implements View.OnTouchListener {
    private static final String TAG = "numView";
    private int[] resIds;
    private LinearLayout linearLayout;
    private ImageView priImg, img;
    private int width, height;
    private int num;//0~9
    private boolean change;

    public NumView(Context context) {
        super(context);
    }

    public NumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {

        setVerticalScrollBarEnabled(false);
        setOnTouchListener(this);

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        addView(linearLayout);
        priImg = new ImageView(context);
        img = new ImageView(context);
        linearLayout.addView(priImg);
        linearLayout.addView(img);
//        setNum(0, false);
    }

    public void setNum(int num, boolean change) {
        setNum(num, -1, change);
    }

    public void setNum(int num, int priNum, boolean change) {
        this.num = num;

        if (priNum == -1) {
            if (num == 0) {
                priNum = 9;
            } else {
                priNum = num - 1;
            }
        }


        if (change) {
            priImg.setImageResource(resIds[priNum]);
            img.setImageResource(resIds[num]);
            anim();
        } else {
            priImg.setImageResource(resIds[num]);
        }
    }

    private void anim() {
        TranslateAnimation animation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_PARENT, -1
        );
        animation.setDuration(500);
        linearLayout.startAnimation(animation);
        handler.sendEmptyMessageDelayed(0, 495);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setNum(num, false);
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        Log.i(TAG, "onSizeChanged: " + height);
    }

    public NumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    public void setResIds(int[] resIds) {
        this.resIds = resIds;
        setNum(0, false);
    }
}
