package com.example.ply.wdplycollect.activity.canvas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ply.wdplycollect.R;

/**
 * 图形绘制demo
 * Created by ply on 2017/3/3.
 */

public class CanvasDemoActivity extends Activity {
    private float[] mScores = {20f, 5f, 30f, 20f, 50f};
    private float[] mScoresAverage = {10f, 23f, 35f, 100f};
    private String[] horizontalCoordinate = {"08日,10日,15日,19日,20日"};
    private float[] mPercent = {0.2f, 0.5f, 0.9f, 0.3f, 0.6f};

    private DrawBitmapMeshView drawBitmapMeshView;
    private DrawProgressView drawProgressView;
    private DrawCharLineView drawCharLineView;
    private DrawRadarCharView drawRadarCharView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);
        //扭曲图像
        drawBitmapMeshView = new DrawBitmapMeshView(this);
        drawBitmapMeshView.start();
        setContentView(drawBitmapMeshView);
        //仪表盘
        drawProgressView = new DrawProgressView(this);
        drawProgressView.setPercent(30);
        setContentView(drawProgressView);
        //折线图
        drawCharLineView = new DrawCharLineView(this);
        drawCharLineView.setDatas(mScores, mScoresAverage, horizontalCoordinate);
        setContentView(drawCharLineView);
        //拓扑图
        drawRadarCharView = new DrawRadarCharView(this);
        drawRadarCharView.setPercent(mPercent);
        setContentView(drawRadarCharView);
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, CanvasDemoActivity.class);
        context.startActivity(intent);
    }
}
