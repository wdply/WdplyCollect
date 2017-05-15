package com.example.ply.wdplycollect.activity.intentAndBroadcaseReceive;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.ply.wdplycollect.R;

/**
 * intent, 广播接收
 * Created by ply on 2017/3/29.
 */

public class IntentActivity extends Activity {
    BatteryBroadcaseReceive batteryBroadcaseReceive;
    SmsBroadcaseReceive smsBroadcaseReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intnet_broadcasereceive);
//        call();
//        callButton();
        callUI();
//        webView();
//        sendEmail();
//        chooseVideo();

//        custom();

//        //动态注册手机电量广播
//        batteryBroadcaseReceive = new BatteryBroadcaseReceive();
//        registerReceiver(batteryBroadcaseReceive, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
//        //注册发送消息广播
//        smsBroadcaseReceive = new SmsBroadcaseReceive();
//        registerReceiver(smsBroadcaseReceive, new IntentFilter("com.example.ply.wdplycollect.smsBroadcaseReceive"));
//        //发送消息广播
//        Intent intent = new Intent("com.example.ply.wdplycollect.smsBroadcaseReceive");
//        intent.putExtra("sms", "这是一条消息");
//        sendBroadcast(intent);

    }

    //调用拨打电话
    public void call() {
        try {
            Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:1234567"));
            startActivity(intentCall);
        } catch (SecurityException e) {
        }
    }

    //通话记录
    public void callButton() {
        Intent intent = new Intent(Intent.ACTION_CALL_BUTTON);
        startActivity(intent);
    }

    //拨号界面
    public void callUI() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1234567"));
        startActivity(intent);
    }

    //浏览网页,使用Android系统中的浏览器
    public void webView() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }

    //发送email
    public void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        //邮件地址
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ply_wd@sina.cn"});
        //抄送地址
        intent.putExtra(Intent.EXTRA_CC, new String[]{"abc@126.com", "text@126.com"});
        //email标题
        intent.putExtra(Intent.EXTRA_SUBJECT, "关于Android的两个技术问题");
        //email内容
        intent.putExtra(Intent.EXTRA_TEXT, "1，如何调用其他应用程序，2，应用程序接受广播");
        //指定email内容是纯文本
        intent.setType("text/plain");
        //Intent.createChooser创建一个自定义的选择器，在Android系统中支持Intent.ACTION_SEND动作的不止有email客户端
        //还有发送短信等，因此会弹出一个选择窗口，选择发送email客户端就可以直接发送
        startActivity(Intent.createChooser(intent, "选择发送email的客户端"));
    }

    //选择相同类型的应用
    public void chooseVideo() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivity(Intent.createChooser(intent, "选择音频设备"));
        //会弹出一个对话框，列出所有支持audio格式的app客户端以供选择
    }

    //自定义action
    public void custom() {
        //跳转到自定义的action 指定的类
        Intent intent = new Intent("com.example.ply.wdplycollect.animaDemo", Uri.parse("direct://12345"));
        startActivity(intent);
        //在指定的类中解析
        String str = getIntent().getData().getHost();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销广播
//        registerReceiver(batteryBroadcaseReceive, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
//        registerReceiver(smsBroadcaseReceive, new IntentFilter("com.example.ply.wdplycollect.smsBroadcaseReceive"));
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, IntentActivity.class);
        context.startActivity(intent);
    }
}
