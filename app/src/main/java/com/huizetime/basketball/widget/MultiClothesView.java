package com.huizetime.basketball.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.nfc.Tag;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.huizetime.basketball.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by water_fairy on 2016/10/12.
 */

public class MultiClothesView extends LinearLayout {

    public static final int WHICH_A = 1;
    public static final int WHICH_B = 2;

    private int selectNum = 0;//可被选中的数量
    private int which;
    private List<ClothesView> mList;

    private HashMap hashMap = new HashMap();


    public MultiClothesView(Context context) {
        super(context);
    }

    public MultiClothesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);

    }

    public MultiClothesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs) {
        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ClothesView clothesView = new ClothesView(context, attrs);
            clothesView.setTag(i);
            clothesView.setOnClothesClickListener(onClothesClick);
            clothesView.setCanClick(true);
            mList.add(clothesView);
            addView(clothesView);
            clothesView.setNum(i);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) clothesView.getLayoutParams();
            layoutParams.height = (int) getResources().getDimension(R.dimen.height_clothes);
            if (i != 4) {
                layoutParams.rightMargin = (int) getResources().getDimension(R.dimen.right_clothes);
            }
        }
    }

    public void canSelectNum(int num) {
        if (num > 5) {
            selectNum = 5;
        }
        if (num <= 0) {
            selectNum = 0;
            for (int i = 0; i < 5; i++) {
                mList.get(i).setChecked(false);
            }
        }
        selectNum = num;
    }

    private int lastPos = -1;
    private OnClickListener onClothesClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (selectNum == 0) {
                return;
            }
//            hashMap.clear();
            ClothesView tempClothesView = (ClothesView) v;
            int tag = (int) v.getTag();

            //监听到衣服被点击
            int num = 0;
            for (int i = 0; i < 5; i++) {
                boolean select = mList.get(i).isSelect();
                if (select) {
                    num++;
                }
            }


            //单选
            if (selectNum == 1) {
                if (num == 0) {
                    tempClothesView.transType();
                } else {
                    if (lastPos == tag) {
                        //相同
                        mList.get(tag).transType();
                    } else {
                        for (int i = 0; i < 5; i++) {
                            ClothesView clothesView = mList.get(i);
                            if (i == tag) {
                                clothesView.setChecked(true);
                            } else {
                                clothesView.setChecked(false);
                            }
                        }
                    }

                }

            } else {
                //多选

                //状态改变前  选中情况(num , hashMap)

                if (selectNum <= num) {
                    if (tempClothesView.isSelect()) {
                        tempClothesView.transType();
                    }
                } else {
                    tempClothesView.transType();
                }
            }


            lastPos = tag;

        }
    };


}
