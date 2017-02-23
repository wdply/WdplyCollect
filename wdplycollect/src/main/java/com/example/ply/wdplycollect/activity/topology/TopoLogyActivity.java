package com.example.ply.wdplycollect.activity.topology;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ply.wdplycollect.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 拓扑图
 * Created by ply on 2017/2/22.
 */

public class TopoLogyActivity extends Activity {
    @BindView(R.id.radarCharView)
    RadarCharView radarCharView;
    private float[] mPercent = {0.2f, 0.5f, 0.9f, 0.3f, 0.6f};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topology);
        ButterKnife.bind(this);

        radarCharView.setPercent(mPercent);
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, TopoLogyActivity.class);
        context.startActivity(intent);
    }
}
