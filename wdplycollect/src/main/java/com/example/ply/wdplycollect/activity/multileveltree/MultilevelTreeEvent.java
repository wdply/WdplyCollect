package com.example.ply.wdplycollect.activity.multileveltree;

/**
 * Created by ply on 2017/2/14.
 */

public class MultilevelTreeEvent {
    //展开，缩放
    public interface ItemChangeListener {

        void onExpand(MultilevelTreeBean treeBean);

        void onContract(MultilevelTreeBean treeBean);
    }
}
