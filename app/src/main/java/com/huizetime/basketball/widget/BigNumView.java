package com.huizetime.basketball.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by water_fairy on 2016/9/27.
 */
public class BigNumView extends LinearLayout {
    private Context mContext;
    private List<NumView> mList;//numView 集合
    private int itemNum;//数量
    private int mLastNum;//上一个数字
    private int[] resIds;//图片资源id
    private AttributeSet attrs;
    private int height;

    public BigNumView(Context context) {
        super(context);
    }

    public BigNumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        mList = new ArrayList<>();

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        setItemNum();
    }

    public BigNumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNum(int num, boolean change) {
        if (num >= Math.pow(10, itemNum)) {
            Log.i("bigNumView", "超过最大值");
            num = (int) (Math.pow(10, itemNum) - 1);
        }

        String strNum = num + "";//即将展示的数据 转为字符串
        String lastStrNum = mLastNum + "";//之前数据

        int len = strNum.length();//即将展示的数据长度
        int priLen = lastStrNum.length();//之前数据
        //拆分数字
        for (int i = 0; i < itemNum; i++) {

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
                mList.get(itemNum - i - 1).setNum(tempNum, priTempNum, change);
            }

        }
        mLastNum = num;
    }

    /**
     * 设置数字位数
     *
     * @param isImg   是否为图片
     * @param itemNum 位数
     * @param divide  分割距离
     */
    public void setItemNum(boolean isImg, int itemNum, int divide) {
        this.itemNum = itemNum;
        this.isImg = isImg;
        this.divide = divide;

    }

    private boolean isImg;
    private int divide;

    private void setItemNum() {
        for (int i = 0; i < itemNum; i++) {
            NumView numView = new NumView(mContext, attrs);

            numView.setHeight(height);
            numView.setImg(isImg);
            if (isImg) {
                numView.setResIds(resIds);
            }
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
     *
     * @param resIds
     */
    public void setResId(int[] resIds) {
        this.resIds = resIds;
        setNum(0, false);
    }
}
