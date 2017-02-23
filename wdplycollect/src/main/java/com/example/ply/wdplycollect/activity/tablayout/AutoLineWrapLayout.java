package com.example.ply.wdplycollect.activity.tablayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.ply.wdplycollect.util.CommonUtils;


/**
 * Created by ljzyuhenda on 16/12/6.
 */

public class AutoLineWrapLayout extends ViewGroup {
    private int mVercicalSpace = 10;
    private int mHorizonSpace = 10;

    public AutoLineWrapLayout(Context context) {
        this(context, null);
    }

    public AutoLineWrapLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLineWrapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData();
    }

    private void initData() {
        mVercicalSpace = CommonUtils.dip2px(getContext(), 11);
        mHorizonSpace = CommonUtils.dip2px(getContext(), 14);
    }

    public void setVercicalSpaceAndHorizonSpace(int mVercicalSpace, int mHorizonSpace) {
        this.mVercicalSpace = CommonUtils.dip2px(getContext(), mVercicalSpace);
        this.mHorizonSpace = CommonUtils.dip2px(getContext(), mHorizonSpace);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpeMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpeSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightSpeMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpeSize = MeasureSpec.getSize(heightMeasureSpec);

        int maxWidth = widthSpeSize - getPaddingLeft() - getPaddingRight();
        int childCount = getChildCount();
        int currentHeight = 0;
        int currentWidthUsed = 0;

        int width = 0;
        int height;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if (maxWidth - currentWidthUsed >= childWidth) {
                //可放下当前view,高度不变
                if (i == 0) {
                    currentHeight += (childHeight + mVercicalSpace);
                } else {
                    currentHeight = currentHeight;
                }

                currentWidthUsed += (childWidth + mHorizonSpace);
            } else {
                //换行
                width = Math.max(width, currentWidthUsed - mHorizonSpace);//减去多加的一个space

                currentHeight += (childHeight + mVercicalSpace);
                currentWidthUsed = childWidth + mHorizonSpace;
            }
        }

        height = (currentHeight - mVercicalSpace) + getPaddingTop() + getPaddingBottom();//减去多加的一个space
        width += (getPaddingLeft() + getPaddingRight());

        if (widthSpeMode == MeasureSpec.EXACTLY) {
            width = widthSpeSize;
        }

        if (heightSpeMode == MeasureSpec.EXACTLY) {
            height = heightSpeSize;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int parentLeft = getPaddingLeft();
        int parentTop = getPaddingTop();
        int childCount = getChildCount();

        int maxWidth = (right - left) - (getPaddingLeft() + getPaddingRight());
        int currentWidthUsed = 0;
        int currentHeight = 0;

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            int childLeft;
            int childTop;
            int childRight;
            int childBottom;
            if (maxWidth - currentWidthUsed >= childWidth) {
                //可放下当前view,高度不变
                if (i == 0) {
                    childLeft = parentLeft;
                    childTop = parentTop;
                    childRight = childLeft + childWidth;
                    childBottom = childTop + childHeight;

                    child.layout(childLeft, childTop, childRight, childBottom);

                    currentWidthUsed += (childWidth + mHorizonSpace);
                } else {
                    childLeft = parentLeft + currentWidthUsed;
                    childTop = parentTop + currentHeight;
                    childRight = childLeft + childWidth;
                    childBottom = childTop + childHeight;

                    child.layout(childLeft, childTop, childRight, childBottom);

                    currentWidthUsed += (childWidth + mHorizonSpace);
                }
            } else {
                currentHeight += (childHeight + mVercicalSpace);

                //换行
                childLeft = parentLeft;
                childTop = parentTop + currentHeight;
                childRight = childLeft + childWidth;
                childBottom = childTop + childHeight;

                child.layout(childLeft, childTop, childRight, childBottom);

                currentWidthUsed = childWidth + mHorizonSpace;
            }
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
