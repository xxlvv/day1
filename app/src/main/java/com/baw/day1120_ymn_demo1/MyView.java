package com.baw.day1120_ymn_demo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Copyright (C)
 * <p>
 * FileName: MyView
 * <p>
 * Author: zhaozhipeng
 * <p>
 * Date: 2019/11/20 16:02
 * <p>
 * Description:
 */
public class MyView extends ViewGroup {

    private Context context;
    private final String string;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyView);
        string = typedArray.getString(R.styleable.MyView_mColor);
        typedArray.recycle();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取子控件
        int childCount = getChildCount();

        //初始化方向
        int s = 20;
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;

        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            //测量子控件
            childAt.measure(0, 0);
            //获取子控件的宽高
            int width = childAt.getMeasuredWidth();
            int height = childAt.getMeasuredHeight();

            left = right + s;
            right = left + width;

            //获取父控件的宽
            int width1 = getWidth();
            if (right > width1) {
                left = s;
                top = bottom + s;
            }
            right = left + width;
            bottom = top + height;

            //摆放布局
            childAt.layout(left, top, right, bottom);
        }
    }

    public void AddTog(String cc) {
        TextView textView = new TextView(context);
        //设置文本大小
        textView.setTextSize(20);
        //设置文本颜色
        textView.setTextColor(Color.parseColor(string));
        //设置文本内容
        textView.setText(cc);
        //设置文本边框
        textView.setBackgroundResource(R.drawable.style);
        //添加视图
        addView(textView);
    }
}
