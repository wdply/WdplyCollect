package com.example.ply.wdplycollect.activity.intentAndBroadcaseReceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.ply.wdplycollect.activity.animation.AnimationDemoActivity;

/**
 * 开机自启动广播
 * Created by ply on 2017/3/29.
 */

public class StartupBroadcaseReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, AnimationDemoActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    }
}
