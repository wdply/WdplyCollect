package com.example.ply.wdplycollect.activity.search;

import android.view.View;

/**
 * Created by ljzyuhenda on 16/4/5.
 */
public interface onRecyclerViewItemClickListener {
    /**
     *
     * @param view 当前点击的View
     * @param position 当前点击的View位置
     * @param resId 当前点击的View的parentView
     */
    void onItemClick(View view, int position, int resId);
}
