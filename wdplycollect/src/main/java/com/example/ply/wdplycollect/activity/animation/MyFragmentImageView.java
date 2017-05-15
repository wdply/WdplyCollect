package com.example.ply.wdplycollect.activity.animation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.ply.wdplycollect.util.CommonUtils;

import java.lang.reflect.Field;

/**
 * 自定义帧动画控件
 * Created by ply on 2017/3/16.
 */

public class MyFragmentImageView extends ImageView {
    public AnimationDrawable animationDrawable;
    public Field field;

    public MyFragmentImageView(Context context) {
        super(context);
    }

    public MyFragmentImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFragmentImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            field = AnimationDrawable.class.getDeclaredField("mCurFrame");
            field.setAccessible(true); //将mCruFrame设为可访问状态
            int curframe = field.getInt(animationDrawable);
            if (curframe == 4) {
                //当播放到第五帧时从第一帧开始
                field.setInt(animationDrawable, 0);
                CommonUtils.showToast(getContext(), "第一帧开始");
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
