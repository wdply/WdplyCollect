package com.example.ply.wdplycollect.activity.multileveltree;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ply.wdplycollect.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 多级知识树适配器
 * Created by KaelLi on 2016/11/18.
 */
public class MultilevelTreeAdapter extends RecyclerView.Adapter<MultilevelTreeAdapter.MultilevelTreeHolder> implements MultilevelTreeEvent.ItemChangeListener {
    private Context mContext;
    private List<MultilevelTreeBean> mList = new ArrayList<>();

    public MultilevelTreeAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MultilevelTreeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MultilevelTreeHolder(LayoutInflater.from(mContext).inflate(R.layout.item_multilevel_tree, parent, false));
    }
    @Override
    public void onBindViewHolder(MultilevelTreeHolder holder, int position) {
        final MultilevelTreeBean bean = mList.get(position);
        holder.tv_title.setText(bean.name);
        holder.tv_info.setText("正确率" + bean.accuracy + "%" + "    已做" + (bean.rnum + bean.wnum) + "/" + bean.qnum);
        List<MultilevelTreeBean> children = bean.children;
        switch (bean.level) {
            case 0:
                if (bean.expand) {
                     holder.line_top.setVisibility(View.INVISIBLE);
                    holder.line_bottom.setVisibility(View.VISIBLE);
                    holder.line_divider.setVisibility(View.INVISIBLE);

                    holder.iv_level_1.setImageResource(R.drawable.icon_expand_1);
                } else {
                    holder.line_top.setVisibility(View.INVISIBLE);
                    holder.line_bottom.setVisibility(View.INVISIBLE);
                    holder.line_divider.setVisibility(View.VISIBLE);

                    if (children != null && children.size() > 0) {
                        holder.iv_level_1.setImageResource(R.drawable.icon_contract_1);
                    } else {
                        holder.iv_level_1.setImageResource(R.drawable.icon_no_1);
                    }
                }
                break;
            case 1:
                if (bean.expand) {
                    holder.line_bottom.setVisibility(View.VISIBLE);
                    holder.line_top.setVisibility(View.VISIBLE);
                    holder.line_divider.setVisibility(View.INVISIBLE);
                    holder.iv_level_1.setImageResource(R.drawable.icon_expand_2);
                } else {
                    holder.line_top.setVisibility(View.VISIBLE);
                    holder.line_bottom.setVisibility(View.VISIBLE);
                    holder.line_divider.setVisibility(View.INVISIBLE);
                    if (children != null && children.size() > 0) {
                        holder.iv_level_1.setImageResource(R.drawable.icon_contract_2);
                    } else {
                        holder.iv_level_1.setImageResource(R.drawable.icon_no_2);
                    }
                    if (bean.isLast) {
                        holder.line_bottom.setVisibility(View.INVISIBLE);
                        holder.line_divider.setVisibility(View.VISIBLE);
                    }
                }
                break;
            default:
                holder.line_top.setVisibility(View.VISIBLE);
                holder.line_bottom.setVisibility(View.VISIBLE);
                holder.line_divider.setVisibility(View.INVISIBLE);
                holder.iv_level_1.setImageResource(R.drawable.icon_expand_3);
                if (bean.isLast && bean.parentTreeBean != null && bean.parentTreeBean.isLast) {
                    holder.line_bottom.setVisibility(View.INVISIBLE);
                    holder.line_divider.setVisibility(View.VISIBLE);
                }
                break;
        }

        holder.rl_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (bean.children == null || bean.children.size() <= 0) {
                    //无子节点
                    return;
                }

                if (bean.expand) {
                    onContract(bean);
                } else {
                    onExpand(bean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void onExpand(MultilevelTreeBean treeBean) {
        //展开
        treeBean.expand = true;
        int position = getCurrentPosition(treeBean.id);
        List<MultilevelTreeBean> child = resetTreeData(treeBean);

        addAll(child, position + 1);// 插入到点击点的下方
    }

    @Override
    public void onContract(MultilevelTreeBean treeBean) {
        //缩放
        treeBean.expand = false;
        int position = getCurrentPosition(treeBean.id);
        List<MultilevelTreeBean> children = treeBean.children;
        removeAll(position + 1, getChildrenCount(treeBean));
    }

    /**
     * 数据初始化
     *
     * @param parentBean 当前点击的节点对象
     * @return
     */
    private List<MultilevelTreeBean> resetTreeData(MultilevelTreeBean parentBean) {
        if (parentBean != null && parentBean.children != null && parentBean.children.size() > 0) {
            for (int i = 0; i < parentBean.children.size(); i++) {
                MultilevelTreeBean tree1 = parentBean.children.get(i);
                tree1.parentTreeBean = parentBean;
                //添加rootid
                if (tree1.level == 0) {
                    tree1.rootParentId = tree1.id;
                } else {
                    tree1.rootParentId = parentBean.id;
                }
                //添加last属性
                if (i == parentBean.children.size() - 1) {
                    tree1.isLast = true;
                }
            }

            return parentBean.children;
        }

        return null;
    }

    /**
     * 批量添加
     *
     * @param list
     * @param position
     */
    public void addAll(List<MultilevelTreeBean> list, int position) {
        mList.addAll(position, list);
        notifyItemRangeInserted(position, list.size());
        notifyItemChanged(position - 1);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        if (mList != null && mList.size() > 0) {
            mList.clear();
        }
    }

    /**
     * 批量删除
     *
     * @param position
     * @param itemCount 删除的数目
     */
    private void removeAll(int position, int itemCount) {
        for (int i = 0; i < itemCount; i++) {
            if (mList != null && mList.size() > position) {
                mList.get(position).expand = false;
                mList.remove(position);
            }
        }
        notifyItemRangeRemoved(position, itemCount);
        notifyItemChanged(position - 1);
    }

    /**
     * 当前id对应的position
     *
     * @param id
     * @return
     */
    private int getCurrentPosition(String id) {
        for (int i = 0; i < mList.size(); i++) {
            if (id.equalsIgnoreCase(mList.get(i).id)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 子节点个数
     *
     * @param item
     * @return
     */
    private int getChildrenCount(MultilevelTreeBean item) {
        int start = getCurrentPosition(item.id);
        int finish = 0;
        for (int i = start + 1; i < mList.size(); i++) {
            if (item.level >= mList.get(i).level) {
                finish = i;
                break;
            }
        }
        if (finish == 0) {
            finish = mList.size();
        }
        return finish - start - 1;
    }

    public class MultilevelTreeHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rl_container)
        RelativeLayout rl_container;
        @BindView(R.id.line_top)
        View line_top;
        @BindView(R.id.line_bottom)
        View line_bottom;
        @BindView(R.id.iv_level_1)
        ImageView iv_level_1;
        @BindView(R.id.tv_title)
        TextView tv_title;
        @BindView(R.id.tv_info)
        TextView tv_info;
        @BindView(R.id.line_divider)
        View line_divider;

        public MultilevelTreeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
