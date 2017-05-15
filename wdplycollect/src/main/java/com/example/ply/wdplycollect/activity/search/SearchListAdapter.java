package com.example.ply.wdplycollect.activity.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ply.wdplycollect.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ljzyuhenda on 16/7/19.
 */
public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.ViewHolderSimulation> {
    private List<ExerciseBean.ExerciseInfoBean> mDatas;
    private Context mContext;
    private onRecyclerViewItemClickListener onItemClickListener;
    private Map<String, String> mTypeMap;
    private String mFontPrefix = "<font color='#ff5403'>";
    private String mFontSubFix = "</font>";
    private String mKeyWord;

    public SearchListAdapter(Context context) {
        mContext = context;

        mTypeMap = new HashMap<>();
        mTypeMap.put("99", "单选项");
        mTypeMap.put("100", "多选项");
        mTypeMap.put("101", "不定项");
        mTypeMap.put("109", "对错题");
        mTypeMap.put("105", "复合题");
    }

    @Override
    public ViewHolderSimulation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_search, null);
        ViewHolderSimulation holderSimulation = new ViewHolderSimulation(view);

        return holderSimulation;
    }

    @Override
    public void onBindViewHolder(ViewHolderSimulation holder, int position) {
        ExerciseBean.ExerciseInfoBean exerciseInfoBean = mDatas.get(position);

        holder.itemView.setTag(R.id.tag_position_item, position);
        holder.tv_label.setText(mTypeMap.get(exerciseInfoBean.type));
        if (TextUtils.isEmpty(mKeyWord)) {
            holder.tv_content.setText(Html.fromHtml(exerciseInfoBean.fragment));
        } else {
            String content = exerciseInfoBean.fragment;
            String prefixReplace = content.replaceAll("<b>", mFontPrefix);
            String subfixReplace = prefixReplace.replaceAll("</b>", mFontSubFix);

            holder.tv_content.setText(Html.fromHtml(subfixReplace));
        }

        holder.tv_info.setText("来源:   " + exerciseInfoBean.from);
    }

    public ExerciseBean.ExerciseInfoBean getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public int getItemCount() {
        if (mDatas == null) {
            return 0;
        }

        return mDatas.size();
    }

    public class ViewHolderSimulation extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_label)
        TextView tv_label;
        @BindView(R.id.tv_content)
        TextView tv_content;
        @BindView(R.id.tv_info)
        TextView tv_info;

        public ViewHolderSimulation(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, (int) view.getTag(R.id.tag_position_item), -1);
            }
        }
    }

    public List<ExerciseBean.ExerciseInfoBean> getDataList() {
        if (mDatas == null) {
            mDatas = new ArrayList<>();
        }

        return mDatas;
    }

    public void setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setKeyWords(String keyWords) {
        mKeyWord = keyWords;
    }
}
