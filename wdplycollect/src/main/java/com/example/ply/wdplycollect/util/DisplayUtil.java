package com.example.ply.wdplycollect.util;

import android.content.Context;
import android.content.res.Resources;


/**
 * Created by saiyuan on 2016/10/26.
 */
public class DisplayUtil {
    private static float density = 0;
    private static float scaledDensity = 0;
    private static int screenWidth = 0;
    private static int screenHeight = 0;
    private static int navigationBarHeight;

    public static int getScreenWidth(Context context) {
        if (screenWidth <= 0) {
            screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        }
        return screenWidth;
    }

    public static int getScreenHeight(Context context) {
        if (screenHeight <= 0) {
            screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        }
        return screenHeight;
    }

    public static float getDensity(Context context) {
        if (density <= 0) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public static float getScaledDensity(Context context) {
        if (scaledDensity <= 0) {
            scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        }
        return scaledDensity;
    }

    /*
    * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
    */
    public static int dp2px(Context context, float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5f);
    }

    public static int px2dp(Context context, float pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5f);
    }

    public static int sp2px(Context context, float pxValue) {
        return (int) (pxValue * getScaledDensity(context) + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        return (int) (pxValue / getScaledDensity(context) + 0.5f);
    }

    /**
     * 获取NavigationBar高度
     *
     * @param activity
     * @return
     */
    public static int getNavigationBarHeight(Context activity) {
        if (navigationBarHeight <= 0) {
            Resources resources = activity.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            navigationBarHeight = resources.getDimensionPixelSize(resourceId);
        }
        return navigationBarHeight;
    }
}
