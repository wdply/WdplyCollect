package com.example.ply.wdplycollect.activity.intentAndBroadcaseReceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 监听电量
 * Created by ply on 2017/3/30.
 */

public class BatteryBroadcaseReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //判断接收的是否是电量变化的broadcase action
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            //当前电量的值
            int lever = intent.getIntExtra("level", 0);
            //scale表示电量的总值
            int scale = intent.getIntExtra("scale", 0);
            //把当前电量换算成百分比的形式
            Log.i("当前电量：", (lever * 100 / scale) + "%");
        }
    }
}
