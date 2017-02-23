package com.example.ply.wdplycollect.activity.multileveltree;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ply.wdplycollect.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节树
 * Created by ply on 2017/2/22.
 */

public class MultilTreeActivity extends Activity {
    private RecyclerView recyclerView;
    private MultilevelTreeAdapter multilevelTreeAdapter;
    private List<MultilevelTreeBean> list_1 = new ArrayList<>();
    private List<MultilevelTreeBean> list_2 = new ArrayList<>();
    private List<MultilevelTreeBean> list_3 = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        multilevelTreeAdapter = new MultilevelTreeAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(multilevelTreeAdapter);
        recyclerView.setItemAnimator(null);

        //三级目录
        for (int i = 0; i < 3; i++) {
            MultilevelTreeBean multilevelTreeBean = new MultilevelTreeBean();
            multilevelTreeBean.id = String.valueOf(10 + i);
            multilevelTreeBean.name = "3-" + (i + 1);
            multilevelTreeBean.accuracy = 20;
            multilevelTreeBean.rnum = 5;
            multilevelTreeBean.wnum = 10;
            multilevelTreeBean.qnum = 30;
            multilevelTreeBean.level = 2;
            multilevelTreeBean.children = null;
            list_3.add(multilevelTreeBean);
        }

        //二级目录
        for (int i = 0; i < 5; i++) {
            MultilevelTreeBean multilevelTreeBean = new MultilevelTreeBean();
            multilevelTreeBean.id = String.valueOf(5 + i);
            multilevelTreeBean.name = "2-" + (i + 1);
            multilevelTreeBean.accuracy = 20;
            multilevelTreeBean.rnum = 5;
            multilevelTreeBean.wnum = 10;
            multilevelTreeBean.qnum = 30;
            multilevelTreeBean.level = 1;
            multilevelTreeBean.children = list_3;
            list_2.add(multilevelTreeBean);
        }

        //一级目录
        for (int i = 0; i < 5; i++) {
            MultilevelTreeBean multilevelTreeBean = new MultilevelTreeBean();
            multilevelTreeBean.id = String.valueOf(i);
            multilevelTreeBean.name = "1-" + (i + 1);
            multilevelTreeBean.accuracy = 20;
            multilevelTreeBean.rnum = 5;
            multilevelTreeBean.wnum = 10;
            multilevelTreeBean.qnum = 30;
            multilevelTreeBean.level = 0;
            multilevelTreeBean.children = list_2;
            list_1.add(multilevelTreeBean);
        }

        multilevelTreeAdapter.addAll(list_1, 0);
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, MultilTreeActivity.class);
        context.startActivity(intent);
    }
}
