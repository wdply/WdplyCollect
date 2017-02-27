package com.example.ply.wdplycollect.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ply.wdplycollect.R;
import com.example.ply.wdplycollect.activity.dashboard.DashBoardActivity;
import com.example.ply.wdplycollect.activity.linechart.LineChartActivity;
import com.example.ply.wdplycollect.activity.localstorage.LocalStorageActivity;
import com.example.ply.wdplycollect.activity.multileveltree.MultilTreeActivity;
import com.example.ply.wdplycollect.activity.tablayout.TabLayoutActivity;
import com.example.ply.wdplycollect.activity.topology.TopoLogyActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new RecyclerviewAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
        private Context context;
        private List<String> list = new ArrayList<>();

        public RecyclerviewAdapter(Context context) {
            this.context = context;
            list.add("章节树");
            list.add("折线图");
            list.add("遥感图");
            list.add("拓扑图");
            list.add("标签自适应");
            list.add("本地存储");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_listview, null));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.textView.setText(list.get(position));
            holder.rl_root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ("章节树".equals(list.get(position))) {
                        MultilTreeActivity.newIntent(context);
                    } else if ("折线图".equals(list.get(position))) {
                        LineChartActivity.newIntent(context);
                    } else if ("遥感图".equals(list.get(position))) {
                        DashBoardActivity.newIntent(context);
                    } else if ("拓扑图".equals(list.get(position))) {
                        TopoLogyActivity.newIntent(context);
                    } else if ("标签自适应".equals(list.get(position))) {
                        TabLayoutActivity.newIntent(context);
                    } else if ("本地存储".equals(list.get(position))) {
                        LocalStorageActivity.newIntent(context);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView textView;
            private RelativeLayout rl_root;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.tv_name);
                rl_root = (RelativeLayout) itemView.findViewById(R.id.rl_root);
            }
        }
    }
}
