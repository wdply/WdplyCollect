package com.example.ply.wdplycollect.activity.animation;

import android.view.animation.Interpolator;

/**
 * 自定义渲染器
 * Created by ply on 2017/3/17.
 */

public class MyTwenInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        if (input <= 0.5) {
            //动画前半段加速
            return input * input;
        } else {
            //动画后半段减速
            return (1 - input) * (1 - input);
        }
    }
}
