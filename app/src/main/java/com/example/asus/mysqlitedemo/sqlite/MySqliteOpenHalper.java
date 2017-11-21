package com.example.asus.mysqlitedemo.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 2017/11/8.
 */

public class MySqliteOpenHalper extends SQLiteOpenHelper {
    private String mname;
    private int mversion;
    public static final String TABLE = "user";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE+" (id integer primary key autoincrement,name text,grade text)";
    public MySqliteOpenHalper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mname = name;
        this.mversion = version;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
