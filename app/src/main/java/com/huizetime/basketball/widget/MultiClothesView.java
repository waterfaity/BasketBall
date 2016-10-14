package com.huizetime.basketball.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Xml;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.huizetime.basketball.R;

import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by water_fairy on 2016/10/12.
 */

public class MultiClothesView extends LinearLayout {
    private Context context;

    private final int TYPE_SET = 1;//来自java代码设置
    private final int TYPE_XML = 2;//来自mxl 配置

    public static final int WHICH_A = 1;
    public static final int WHICH_B = 2;

    private int clothesNum = 5;//默认5个

    private int selectNum = 0;//可被选中的数量
    private int which;//AB队
    private List<LinearLayout> mRowList;//每列集合
    private List<ClothesView> mClothesList;//衣服集合
    private OnClothesClickListener onClothesClickListener;

    private HashMap hashMap = new HashMap();


    public MultiClothesView(Context context) {
        super(context);
    }

    public MultiClothesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context, attrs, TYPE_XML);

    }

    public MultiClothesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int type) {
        removeAllViews();
            mClothesList = new ArrayList<>();

        int rows = clothesNum / 5;
        for (int i = 0; i < rows; i++) {
            LinearLayout linearLayout = new LinearLayout(context);
            addView(linearLayout);//添加行数
            LinearLayout.LayoutParams layoutParams = (LayoutParams) linearLayout.getLayoutParams();
            if ((clothesNum == 2 && i == 0) || (rows == 3 && (i == 0 || i == 1))) {
                //每列中间间距
                layoutParams.bottomMargin =
                        (int) getResources().getDimension(R.dimen.top_clothes_lin);
            }
            layoutParams.height = (int) getResources().getDimension(R.dimen.height_clothes);
            linearLayout.setLayoutParams(layoutParams);
            for (int j = 0; j < 5; j++) {

                ClothesView clothesView = null;
                if (type == TYPE_XML) clothesView = new ClothesView(context, attrs);
                else clothesView = new ClothesView(context, which);


                clothesView.setTag(i * 5 + j);//设置tag 0,1,2,---,13,14
                clothesView.setOnClothesClickListener(onClothesClick);
                clothesView.setCanClick(true);
                clothesView.setNum(i * 5 + j + 1);
                mClothesList.add(clothesView);
                linearLayout.addView(clothesView);//每行添加衣服数量
                LinearLayout.LayoutParams clothesViewLayoutParams = (LayoutParams) clothesView.getLayoutParams();
                clothesViewLayoutParams.height = (int) getResources().getDimension(R.dimen.height_clothes);
                if (j != 4) {
                    //除每行最后一个 设置右边距离
                    clothesViewLayoutParams.rightMargin = (int) getResources().getDimension(R.dimen.right_clothes);
                }
                clothesView.setLayoutParams(clothesViewLayoutParams);
            }
        }
    }

    private int lastPos = -1;
    private OnClickListener onClothesClick = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (onClothesClickListener != null)
                onClothesClickListener.onClothesClick(MultiClothesView.this, (ClothesView) v);
            if (selectNum == 0) {
                return;
            }
            ClothesView tempClothesView = (ClothesView) v;
            int tag = (int) v.getTag();

            //监听到衣服被点击
            int num = 0;
            for (int i = 0; i < clothesNum; i++) {
                boolean select = mClothesList.get(i).isSelect();
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
                        mClothesList.get(tag).transType();
                    } else {
                        for (int i = 0; i < clothesNum; i++) {
                            ClothesView clothesView = mClothesList.get(i);
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

    //设置衣服数量(5 10 15) ,球队
    public void initMultiClothesView(int num, int which) {
        this.clothesNum = num;
        this.which = which;
        initView(context, null, TYPE_SET);
    }

    //设置AB队
    public void setWhich(int which) {
        this.which = which;
        for (int i = 0; i < mClothesList.size(); i++) {
            mClothesList.get(i).setType(which);
        }
    }

    //设置可被选中的数量
    public void SetCanSelectNum(int num) {
        if (num <= 0) {
            selectNum = 0;
            for (int i = 0; i < clothesNum; i++) {
                mClothesList.get(i).setChecked(false);
            }
        }
        selectNum = num;
    }

    //获取选中的衣服
    public List<ClothesView> getChecked() {
        List<ClothesView> list = new ArrayList<>();
        for (int i = 0; i < clothesNum; i++) {
            ClothesView clothesView = mClothesList.get(i);
            if (clothesView.isSelect()) list.add(clothesView);
        }
        return list;
    }

    public interface OnClothesClickListener {
        void onClothesClick(MultiClothesView multiClothesView, ClothesView clothesView);
    }

    public void setOnClothesClickListener(OnClothesClickListener listener) {
        onClothesClickListener = listener;
    }
}
