package com.example.ply.wdplycollect.activity.intentAndBroadcaseReceive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 接收消息广播
 * Created by ply on 2017/3/29.
 */

public class SmsBroadcaseReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("com.example.ply.wdplycollect.smsBroadcaseReceive".equals(intent.getAction())) {
            String sms = intent.getStringExtra("sms");
            Log.i("sms", sms);
        }

//        StringBuilder sb = new StringBuilder();
//        //接收有SMS传过来的数据
//        Bundle bundle = intent.getExtras();
//        if (bundle != null) {
//            //通过pdus可以接收到所有的短信，objects中每一个对象是一条短信
//            Object[] objects = (Object[]) bundle.get("pdus");
//            //构建短信对象
//            SmsMessage[] smsMessages = new SmsMessage[objects.length];
//            for (int i = 0; i < objects.length; i++) {
//                //将objects对象中的每一条信息转换成smsMessage对象
//                smsMessages[i] = SmsMessage.createFromPdu((byte[]) objects[i]);
//            }
//            for (SmsMessage smsMessage : smsMessages) {
//                sb.append("短信来源");
//                //获取接受短信的电话号码
//                sb.append(smsMessage.getDisplayOriginatingAddress());
//                sb.append("\n--------短信内容----------\n");
//                sb.append(smsMessage.getDisplayMessageBody());
//            }
//            //控制台打印所有消息
//            Log.i("smsMessage", sb.toString());
//        }
    }
}
