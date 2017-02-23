package com.example.ply.wdplycollect.activity.multileveltree;

import java.util.List;

/**
 * Created by KaelLi on 2016/11/18.
 */
public class MultilevelTreeBean {
    public String id;//知识点id
    public String name;//知识点名称
    public int qnum;//总题数
    public int unum;//未做题数
    public double accuracy;//正确率
    public int rnum;//答对题数
    public int wnum;//答错题数
    public long times;//做题总花费时间
    public long speed;//做题平均时间
    public int level;//节点级别
    public List<MultilevelTreeBean> children;//子节点
    public int parent = 0; //父节点id,根为0

    public int type;// 显示类型
    public String text;
    public String path;// 路径
    public int treeDepth = 0;// 路径的深度
    public List<String> questions;

    public boolean expand;// 是否展开
    public boolean isLast; // 是否是最后一个
    public String rootParentId; // 顶级节点的id
    public MultilevelTreeBean parentTreeBean;//父节点数据

}
