package com.huizetime.basketball.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huizetime.basketball.R;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;

/**
 * Created by water_fairy on 2016/10/12.
 */

public class ClothesView extends LinearLayout implements View.OnClickListener {
    public static final int TYPE_A = 1;//A队正常
    public static final int TYPE_B = 2;//B队正常
    public static final int TYPE_A_SELECT = 3;//A队选中
    public static final int TYPE_B_SELECT = 4;//B队选中
    public static final int TYPE_NORMAL = 5;//一般

    private int clothType = TYPE_A;//衣服资源id
    private int clothesNum;//球衣号
    private float imgHeight, imgWidth, numSize;


    private ImageView imageView;//球衣
    private TextView textView;//球衣号
    private RelativeLayout relativeLayout;//包含球衣,球衣号

    private OnClickListener onClickListener;

    private boolean canClick = true;

    public ClothesView(Context context) {
        super(context);
    }

    public ClothesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClothesView);
        imgHeight = typedArray.getDimension(R.styleable.ClothesView_imgHeight, 10);
        imgWidth = typedArray.getDimension(R.styleable.ClothesView_imgWidth, 10);
        numSize = typedArray.getDimension(R.styleable.ClothesView_numSize, 20);
        String num = typedArray.getString(R.styleable.ClothesView_clothesNum);
        if (!TextUtils.isEmpty(num)) {
            clothesNum = Integer.parseInt(num);
        }
        String type = typedArray.getString(R.styleable.ClothesView_clothesType);
        if (!TextUtils.isEmpty(type)) {
            clothType = Integer.parseInt(type);
        }
        initView(context);

    }

    private void initView(Context context) {
        relativeLayout = new RelativeLayout(context);
        addView(relativeLayout);
        LinearLayout.LayoutParams layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
        layoutParams.height = (int) imgHeight;
        layoutParams.width = (int) imgWidth;
        layoutParams.gravity = Gravity.BOTTOM;

        //球衣
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.a_clothes_normal));
        //球衣num
        textView = new TextView(context);
        textView.setText(clothesNum + "");
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(numSize);

        relativeLayout.addView(imageView);
        relativeLayout.addView(textView);
        imageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        imageView.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
        ((RelativeLayout.LayoutParams) textView.getLayoutParams()).addRule(CENTER_IN_PARENT);
        imageView.setOnClickListener(this);
        setType(clothType);
    }

    public ClothesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setNum(int num) {
        this.clothesNum = num;
        textView.setText(clothesNum + "");

    }

    /**
     * 点击后转换状态
     */
    public void transType() {
        switch (clothType) {
            case TYPE_A:
                clothType = TYPE_A_SELECT;
                break;
            case TYPE_B:
                clothType = TYPE_B_SELECT;
                break;
            case TYPE_A_SELECT:
                clothType = TYPE_A;
                break;
            case TYPE_B_SELECT:
                clothType = TYPE_B;
                break;
            case TYPE_NORMAL:
                break;
        }
        setType(clothType);

    }

    /**
     * 直接设置状态
     *
     * @param type
     */
    public void setType(int type) {
        if (type == 0 || type > 5) {
            return;
        }
        int resId = 0;
        LinearLayout.LayoutParams layoutParams =
                (LayoutParams) relativeLayout.getLayoutParams();
        switch (clothType) {
            case TYPE_A:
                resId = R.mipmap.a_clothes_normal;
                layoutParams.gravity = Gravity.BOTTOM;
                break;
            case TYPE_B:
                resId = R.mipmap.b_clothes_normal;
                layoutParams.gravity = Gravity.BOTTOM;
                break;
            case TYPE_A_SELECT:
                resId = R.mipmap.a_clothes_select;
                layoutParams.gravity = Gravity.TOP;
                break;
            case TYPE_B_SELECT:
                resId = R.mipmap.b_clothes_select;
                layoutParams.gravity = Gravity.TOP;
                break;
            case TYPE_NORMAL:
                resId = R.mipmap.clothes;
                layoutParams.gravity = Gravity.BOTTOM;
                break;
        }
        relativeLayout.setLayoutParams(layoutParams);
        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), resId));
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (onClickListener != null) {
            onClickListener.onClick(this);
        } else {
            transType();
        }

    }


    public int getNum() {
        return clothesNum;
    }

    public boolean isSelect() {
        if (clothType == TYPE_A_SELECT || clothType == TYPE_B_SELECT) {
            return true;
        } else {
            return false;
        }
    }


    public void setCanClick(boolean canClick) {
        this.canClick = canClick;
    }

    public void setOnClothesClickListener(OnClickListener clickListener) {
        this.onClickListener = clickListener;
    }


    public void setChecked(boolean checked) {
        if (checked) {
            if (clothType == TYPE_A || clothType == TYPE_B) {
                transType();
            }
        } else {
            if (clothType == TYPE_A_SELECT || clothType == TYPE_B_SELECT) {
                transType();
            }
        }

    }
}
