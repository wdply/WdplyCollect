package com.example.ply.wdplycollect.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;


/** 公共对话框类
 * Created by ply on 2015/7/8.
 */
public class CustomAlertDialogForPublic {
    public TextView tv_content;
    private TextView ok;
    private TextView cancel;
    private AlertDialog alertDialog;
    private int resourceID;
    private EditText ed_context;

    public CustomAlertDialogForPublic(Context context, int resourceID) {
        alertDialog = new AlertDialog.Builder(context).create();
        this.resourceID = resourceID;
        initDlg();
    }

    private void initDlg() {
        alertDialog.show();
        Window window = alertDialog.getWindow();
        // *** 主要就是在这里实现这种效果的.
        // 设置窗口的内容页面,item_mainactivity_dialog.xml文件中定义view内容
        window.setContentView(resourceID);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);

//        //设置title
//        tv_content = (TextView) window.findViewById(R.id.tv_dialog_content);
//        ed_context = (EditText) window.findViewById(R.id.ed_password);
//        ok = (TextView) window.findViewById(R.id.tv_dialog_ok);
//        // 关闭alert对话框架
//        cancel = (TextView) window.findViewById(R.id.tv_dialog_cancel);
//        if(ed_context != null){
//            //下面两行代码加入后即可弹出输入法
//            alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//            alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
//        }
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

    public String getEditContext() {
        return ed_context.getText().toString().replace(" ", "");
    }

    public void setCancelable(boolean isCancelable) {
        alertDialog.setCancelable(isCancelable);
    }

    public void dismiss() {
        alertDialog.dismiss();
    }

    public void show() {
        alertDialog.show();
    }

    public View findViewById(int resourceID) {
        return alertDialog.findViewById(resourceID);
    }
}

