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
    private List<NumView> mList;//numView 集合
    private int mItemNum;//数量
    private int mLastNum;//上一个数字
    private int[] resIds;//图片资源id
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
        if (num >= Math.pow(10, mItemNum)) {
            Log.i("bigNumView", "超过最大值");
            num = (int) (Math.pow(10, mItemNum) - 1);
        }

        String strNum = num + "";//即将展示的数据 转为字符串
        String lastStrNum = mLastNum + "";//之前数据

        int len = strNum.length();//即将展示的数据长度
        int priLen = lastStrNum.length();//之前数据
        //拆分数字
        for (int i = 0; i < mItemNum; i++) {

            int tempNum = 0;//
            int priTempNum = 0;
            //从个位开始
            if (len >= i + 1) {
                String temp1 = strNum.charAt(len - i - 1) + "";
                tempNum = Integer.parseInt(temp1);
            }
            if (priLen >= i + 1) {
                String temp2 = lastStrNum.charAt(priLen - i - 1) + "";
                priTempNum = Integer.parseInt(temp2);
            }
            //相对应的数字切换 相同不再切换
            if (tempNum != priTempNum) {
                mList.get(mItemNum - i - 1).setNum(tempNum, priTempNum, change);
            }

        }
        mLastNum = num;
    }

    /**
     * 设置数字位数
     *
     * @param itemNum 位数
     * @param divide  分割距离
     */
    public void setItemNum(int itemNum, int divide) {
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

    /**
     * 设置资源id   0-9
     * @param resIds
     */
    public void setResId(int[] resIds) {
        this.resIds = resIds;
        setNum(0, false);
    }
}
