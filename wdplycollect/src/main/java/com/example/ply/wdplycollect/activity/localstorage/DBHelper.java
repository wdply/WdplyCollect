package com.example.ply.wdplycollect.activity.localstorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库管理辅助类
 * 用于管理数据库的创建和数据库的版本更新
 * Created by ply on 2017/2/27.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME11 = "wdply.db";//数据库名称
    public static int DB_VERDION = 1;//数据库版本从1开始

    public DBHelper(Context context) {
        super(context, DB_NAME11, null, DB_VERDION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //onCreate方法一般用来首次创建数据库时建立表，师徒等数据库组件
        db.execSQL("create table wdply(_id integer primary key autoincrement,name varchar ,age int,sex varchat,tel varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //当前传递的版本号高于原始数据库版本号时会调用该方法，用于数据库中表结构的更新
        //执行该方法，一般会首先删除要升级的表，视图等软件，在重新创建他们。
        db.execSQL("drop table if exists[wdply]");
        //重新创建
        db.execSQL("create table wdply(_id integer primary key autoincrement,name varchar ,age int,sex varchat,tel varchar)");
    }
}
