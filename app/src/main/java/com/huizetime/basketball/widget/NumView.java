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
import android.widget.TextView;

import com.huizetime.basketball.R;

/**
 * Created by water_fairy on 2016/9/27.
 */
public class NumView extends ScrollView implements View.OnTouchListener {
    private static final String TAG = "numView";
    private int[] resIds;//资源ids
    private LinearLayout linearLayout;//数字插入的位置 垂直布局
    private ImageView priImg, img;//上图,下图
    private TextView priTV, TV;//上数字,下数字
    private int num;//0~9  设置显示的数据
    private boolean isImg = true;
    private Context context;
    private int height=-1;


    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isImg() {
        return isImg;
    }

    public void setImg(boolean img) {
        isImg = img;
        setContentType(isImg);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public NumView(Context context) {
        super(context);
    }

    public NumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context);
    }

    private void initView(Context context) {

        setVerticalScrollBarEnabled(false);
        setOnTouchListener(this);

//        linearLayout = new LinearLayout(context);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        addView(linearLayout);
//        priImg = new ImageView(context);
//        img = new ImageView(context);
//        linearLayout.addView(priImg);
//        linearLayout.addView(img);
        setContentType(isImg);
        setBackgroundResource(R.drawable.bg_score_num);
    }

    private void setContentType(boolean isImg) {
        if (isImg) {
            removeAllViews();
            linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            addView(linearLayout);
            priImg = new ImageView(context);
            img = new ImageView(context);
            linearLayout.addView(priImg);
            linearLayout.addView(img);
            priImg.getLayoutParams().height = height;
            img.getLayoutParams().height = height;
        } else {
            removeAllViews();
            linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            addView(linearLayout);
            priTV = new TextView(context);
            TV = new TextView(context);
            linearLayout.addView(priTV);
            linearLayout.addView(TV);
            priTV.setTextColor(getResources().getColor(R.color.white));
            TV.setTextColor(getResources().getColor(R.color.white));
            priTV.getLayoutParams().height = height;
            TV.getLayoutParams().height = height;
        }
    }

    /**
     * 设置数字
     *
     * @param num
     * @param change 是否有动画
     */
    public void setNum(int num, boolean change) {
        setNum(num, -1, change);
    }

    /**
     * 设置数字
     *
     * @param num    现在的数字
     * @param priNum 前一个数字
     * @param change 是否有动画
     */
    public void setNum(int num, int priNum, boolean change) {
        this.num = num;
//若priNum==-1  前一个数字默认设置为  num-1
        if (priNum == -1) {
            //当前为0 ,前一个为9
            if (num == 0) {
                priNum = 9;
            } else {
                priNum = num - 1;
            }
        }


        if (change) {
            if (isImg) {
                priImg.setImageResource(resIds[priNum]);
                img.setImageResource(resIds[num]);
            } else {
                priTV.setText(priNum + "");
                TV.setText(num + "");
            }
            anim();

        } else {
            if (isImg) {

                priImg.setImageResource(resIds[num]);
            } else {
                priTV.setText(num + "");
            }
        }

    }

    //动画 after(false)
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
