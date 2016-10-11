package com.huizetime.basketball.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huizetime.basketball.R;

/**
 * Created by water_fairy on 2016/9/27.
 */
public class ScoreView extends LinearLayout {

    private int score;
    private TextView score1, score2, score3;
    private int width, height;
    private int textSize;

    public ScoreView(Context context) {
        super(context);
    }

    public ScoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ScoreView);
        textSize = a.getDimensionPixelSize(R.styleable.ScoreView_textSize, 20);
        height = a.getDimensionPixelSize(R.styleable.ScoreView_viewHeight, 20);

        for (int i = 0; i < 3; i++) {
            TextView textView = new TextView(context);
            if (i == 0) {
                score1 = textView;
            } else if (i == 1) {
                score2 = textView;
            } else {
                score3 = textView;
            }
            textView.setText("" + 0);
            textView.setTextSize(textSize);
            textView.setTextColor(getResources().getColor(R.color.white));
            textView.setBackgroundResource(R.drawable.bg_score_single);
            textView.setGravity(Gravity.CENTER);
            addView(textView);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
            layoutParams.weight = 1;
            layoutParams.height = height;
            if (i != 2) {
                layoutParams.rightMargin = 2;
            }

        }
    }


    public void setScore(int score) {
        boolean isFu = false;
        if (score < 0) {
            isFu = true;
            score = Math.abs(score);
        }
        if (score > 999) {
            score = 999;
        }
        this.score = score;
        String[] split = (score + "").split("");
        int length = split.length;
        if (split.length == 4) {
            score1.setText(split[1]);
            score2.setText(split[2]);
            score3.setText(split[3]);
        } else if (length == 3) {
            score1.setText("0");
            score2.setText(split[1]);
            score3.setText(split[2]);
        } else if (length == 2) {
            score1.setText("0");
            score2.setText("0");
            score3.setText(split[1]);
        }
        if (isFu) {
            score1.setText("-");
        }
    }
}
