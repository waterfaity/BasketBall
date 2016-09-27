package com.huizetime.basketball.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2016/9/27.
 */
public class BigNumView extends LinearLayout {
    private Context mContext;
    private List<NumView> mList;//numView 的数量
    private int mItemNum;//数量
    private int mNum;//数字
    private int mLastNum;
    private int mDivide;
    private int[] resIds;
    private AttributeSet attrs;

    public BigNumView(Context context) {
        super(context);
    }

    public BigNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        mList = new ArrayList<>();

    }

    public BigNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNum(int num, boolean change) {
        this.mNum = num;
        if (num >= Math.pow(10, mItemNum)) {
            Log.i("bigNumView", "超过最大值");
            num = (int) (Math.pow(10, mItemNum) - 1);
        }
//110  200
        String strNum = num + "";
        String lastStrNum = mLastNum + "";

        int len = strNum.length();
        int priLen = lastStrNum.length();

        for (int i = 0; i < mItemNum; i++) {
            int tempNum = 0;
            int priTempNum = 0;
            if (len >= i + 1) {
                String temp1 = strNum.charAt(len - i - 1) + "";
                tempNum = Integer.parseInt(temp1);
            }
            if (priLen >= i + 1) {
                String temp2 = lastStrNum.charAt(priLen - i - 1) + "";
                priTempNum = Integer.parseInt(temp2);
            }
            if (tempNum != priTempNum) {
                mList.get(mItemNum - i - 1).setNum(tempNum, priTempNum, change);
            }

        }
        mLastNum = num;
    }

    public void setItemNum(int itemNum, int divide) {
        this.mDivide = divide;
        this.mItemNum = itemNum;
        for (int i = 0; i < itemNum; i++) {
            NumView numView = new NumView(mContext, attrs);
            numView.setResIds(resIds);
            addView(numView);
            mList.add(numView);
            if (i != itemNum - 1) {
                LinearLayout.LayoutParams lay =
                        (LayoutParams) numView.getLayoutParams();
                lay.rightMargin = divide;
            }

        }
    }

    public void setResId(int[] resIds) {
        this.resIds = resIds;
        setNum(0, false);
    }
}
