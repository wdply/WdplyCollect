package com.example.ply.wdplycollect.activity.localstorage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.ply.wdplycollect.R;
import com.example.ply.wdplycollect.util.CommonUtils;

import java.io.File;

/**
 * Android 存储
 * Created by ply on 2017/2/24.
 */

public class LocalStorageActivity extends Activity {
    String text = "Android 本地存储";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localstorage);
        //内存存储
//        String filepath1 = getCacheDir().getAbsolutePath() + File.separator + "wdply.txt";//data/data/包命/cache/wdply.txt
        String filePath2 = getFilesDir().getAbsolutePath() + File.separator + "wdply.txt";//data/data/包命/files/wdply.txt
        CommonUtils.setLocalData(filePath2, text);//保存数据
        String str = CommonUtils.getLocalData(filePath2);//读取数据
        CommonUtils.showToast(this, str);

        //sdk存储
//        String path1 = Environment.getExternalStorageDirectory().getAbsolutePath();//  /storage/emulated/0
//        String path2 = getExternalCacheDir().getAbsolutePath() + File.separator + "wdply.txt";//  /storage/emulated/0/Android/data/包名/cache/wdply.txt
        String path3 = getExternalFilesDir(null).getAbsolutePath() + File.separator + "wdply.txt";//  /storage/emulated/0/Android/data/包名/files/wdply.txt
        CommonUtils.saveSdCardData(path3, text);//保存数据
        String content = CommonUtils.getSdCardData(path3);//读取数据
        CommonUtils.showToast(this, content);

        //数据库存储
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        db.execSQL("insert into wdply values(null,?,?,?,?)", new Object[]{"小明", 1, "男", "159"});
        db.execSQL("update wdply set sex=? where name=?", new Object[]{"女", "小明"});
        db.execSQL("delete from wdply where name=?", new Object[]{"小明"});
        db.execSQL("delete from wdply");
        Cursor cursor = db.rawQuery("select * from wdply", null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String sex = cursor.getString(3);
            Log.i("tag", "姓名:" + name + "年龄：" + age + "性别:" + sex);
        }
    }

    public static void newIntent(Context context) {
        Intent intent = new Intent(context, LocalStorageActivity.class);
        context.startActivity(intent);
    }
}
