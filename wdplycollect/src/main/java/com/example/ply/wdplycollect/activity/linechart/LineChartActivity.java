package com.example.ply.wdplycollect.activity.linechart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ply.wdplycollect.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 折线图
 * Created by ply on 2017/2/22.
 */

public class LineChartActivity extends Activity {
    @BindView(R.id.graphView)
    GraphView graphView;

    private float[] mScores = {20f, 5f, 30f, 20f, 50f};
    private float[] mScoresAverage = {10f, 23f, 35f, 100f};
    private String[] horizontalCoordinate = {"08日,10日,15日,19日,20日"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_linechart);
        ButterKnife.bind(this);

        graphView.setDatas(mScores, mScoresAverage, horizontalCoordinate);
    }
    public static void newIntent(Context context) {
        Intent intent = new Intent(context, LineChartActivity.class);
        context.startActivity(intent);
    }
}
