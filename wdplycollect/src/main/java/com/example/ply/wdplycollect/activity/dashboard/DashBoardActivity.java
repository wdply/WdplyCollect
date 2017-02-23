package com.example.ply.wdplycollect.activity.dashboard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ply.wdplycollect.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 仪表盘
 * Created by ply on 2017/2/22.
 */

public class DashBoardActivity extends Activity {
    @BindView(R.id.progressView)
    ProgressView progressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        progressView.setPercent(30);
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, DashBoardActivity.class);
        context.startActivity(intent);
    }
}
