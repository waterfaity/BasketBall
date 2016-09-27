package com.huizetime.basketball.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by water_fairy on 2016/9/27.
 */
public class ScoreView extends LinearLayout {

    private int score;
    private TextView score1, score2, score3;
    private int width, height;

    public ScoreView(Context context) {
        super(context);
    }

    public ScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        score1 = new TextView(context);
        score2 = new TextView(context);
        score3 = new TextView(context);
        addView(score1);
        addView(score2);
        addView(score3);
    }

    public ScoreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

    }

    public void setScore(int score) {
        this.score = score;
    }
}
