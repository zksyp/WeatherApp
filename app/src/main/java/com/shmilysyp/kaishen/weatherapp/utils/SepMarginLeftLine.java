package com.shmilysyp.kaishen.weatherapp.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kaishen on 16/10/27.
 */

public class SepMarginLeftLine extends RecyclerView.ItemDecoration {

    /**
     * 垂直方向
     */
    public static final int VERTICAL = LinearLayoutManager.VERTICAL;
    public static final int DEFAULT_SIZE = 1;
    public static final String DEFAULT_COLOR = "#E7E7E7";

    // 画笔
    private Paint paint;

    private int leftMargin;
    // 分割线颜色
    private int color;
    //背景颜色
    private int bgColor;
    // 分割线尺寸
    private int size;

    public SepMarginLeftLine() {
        paint = new Paint();
        color = Color.parseColor(DEFAULT_COLOR);
        bgColor = Color.WHITE;
        leftMargin = Utility.dip2px(15f);
        size = DEFAULT_SIZE;
    }

    public SepMarginLeftLine(int leftMargin) {
        paint = new Paint();
        color = Color.parseColor(DEFAULT_COLOR);
        bgColor = Color.WHITE;
        this.leftMargin = Utility.dip2px(leftMargin);
        size = DEFAULT_SIZE;
    }

    /**
     * 设置分割线颜色
     */
    public void setColor(int color) {
        this.color = color;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    /**
     * 设置分割线尺寸
     */
    public void setHeight(int size){this.size = size;}

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, size);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
    }

    /**
     * 绘制垂直分割线
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getLeft();
        final int right = parent.getRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + size;
            paint.setColor(bgColor);
            c.drawRect(left, top, left + leftMargin, bottom, paint);
            paint.setColor(color);
            c.drawRect(left + leftMargin, top, right, bottom, paint);
        }
    }
}
