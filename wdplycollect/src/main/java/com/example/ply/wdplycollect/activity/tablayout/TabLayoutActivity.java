package com.example.ply.wdplycollect.activity.tablayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.ply.wdplycollect.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 标签自适应
 * Created by ply on 2017/2/22.
 */

public class TabLayoutActivity extends Activity {
    @BindView(R.id.autoline)
    AutoLineWrapLayout autoline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);

        autoline.setVercicalSpaceAndHorizonSpace(10, 21);
        TextView tv_bc = (TextView) LayoutInflater.from(this).inflate(R.layout.tv_course, null);
        tv_bc.setText("北京");
        TextView tv_bb = (TextView) LayoutInflater.from(this).inflate(R.layout.tv_course, null);
        tv_bb.setText("琅琊榜");
        TextView tv_kcsm = (TextView) LayoutInflater.from(this).inflate(R.layout.tv_course, null);
        tv_kcsm.setText("全国人大代表人大代表人大");
        TextView tv_accomodation = (TextView) LayoutInflater.from(this).inflate(R.layout.tv_course, null);
        tv_accomodation.setText("中国人名代表大会中国人名代表中国");
        TextView tv_a = (TextView) LayoutInflater.from(this).inflate(R.layout.tv_course, null);
        tv_a.setText("圣诞节发斯蒂");
        autoline.addView(tv_bc);
        autoline.addView(tv_bb);
        autoline.addView(tv_kcsm);
        autoline.addView(tv_accomodation);
        autoline.addView(tv_a);
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, TabLayoutActivity.class);
        context.startActivity(intent);
    }
}
