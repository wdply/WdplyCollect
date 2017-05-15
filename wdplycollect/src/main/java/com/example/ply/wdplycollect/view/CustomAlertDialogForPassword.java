package com.example.ply.wdplycollect.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ply.wdplycollect.R;
import com.gridpasswordview.GridPasswordView;


/**
 * 输入密码对话框
 * Created by ply on 2015/7/8.
 */
public class CustomAlertDialogForPassword {
    private TextView tv_content;
    private TextView ok;
    private TextView cancel;
    private TextView tv_goldNumber;
    private AlertDialog alertDialog;
    private GridPasswordView gridPasswordView;

    public CustomAlertDialogForPassword(Context context) {
        alertDialog = new AlertDialog.Builder(context).create();
        initDlg();
    }

    private void initDlg() {
        alertDialog.show();
        //下面两行代码加入后即可弹出输入法
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = alertDialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,item_mainactivity_dialog.xml文件中定义view内容
        window.setContentView(R.layout.dialog_password);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        gridPasswordView = (GridPasswordView) window.findViewById(R.id.gpv_passwordType);
        //设置title
        tv_content = (TextView) window.findViewById(R.id.tv_dialog_content);
        ok = (TextView) window.findViewById(R.id.tv_dialog_ok);
        // 关闭alert对话框架
        cancel = (TextView) window.findViewById(R.id.tv_dialog_cancel);
        tv_goldNumber = (TextView) window.findViewById(R.id.tv_goldNumber);
    }

    public void setOkOnClickListener(View.OnClickListener okOnClickListener) {
        ok.setOnClickListener(okOnClickListener);
    }

    public void setCancelOnClickListener(View.OnClickListener cancelOnClickListener) {
        cancel.setOnClickListener(cancelOnClickListener);
    }

    public void setTitle(String title) {
        tv_content.setText(Html.fromHtml(title));
    }

    public void setOkTitle(String title) {
        ok.setText(title);
    }

    public void setCannelTitle(String title) {
        tv_content.setText(title);
    }

    public String getPasswordContext() {
        return gridPasswordView.getPassWord();
    }

    public void setGoldNumber(String number) {
        tv_goldNumber.setText("购买该课程将消耗您￥" + number);
    }

    public void setInVisivleGoldNumber() {
        tv_goldNumber.setVisibility(View.GONE);
    }

    public void setCancelable(boolean isCancelable) {
        alertDialog.setCancelable(isCancelable);
    }

    public void dismiss() {
        alertDialog.dismiss();
    }
}

