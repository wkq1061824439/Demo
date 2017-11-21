package com.example.asus.mysqlitedemo.service;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;

import com.example.asus.mysqlitedemo.bean.User;
import com.example.asus.mysqlitedemo.sqlite.MySqliteOpenHalper;

import java.util.ArrayList;

public class MyService extends Service {

    private MyBinder binder;
    private MySqliteOpenHalper mySqlite;
    private SQLiteDatabase db;

    public MyService() {

    }

    public class MyBinder extends Binder{
        public void findAll(final ArrayList<User> lists){
            new Thread(new Runnable() {
                Cursor cursor = db.rawQuery("SELECT * FROM " + MySqliteOpenHalper.TABLE + " ", null);
                @Override
                public void run() {
                    while (cursor.moveToNext()){
                        User user = new User();
                        Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String grade = cursor.getString(cursor.getColumnIndex("grade"));
                        user.setId(id);
                        user.setName(name);
                        user.setGrade(grade);
                        lists.add(user);
                    }
                }
            }).start();
        }

        public void add(String name,String grade){
            db.execSQL("INSERT INTO user (name,grade) VALUES('"+name+"','"+grade+"')  ");
        }

        public void del(String uid){
            db.execSQL("DELETE FROM user where id = "+uid+" ");
        }


        public void update(String uid, String name, String grade) {
            db.execSQL("UPDATE user SET name = '"+name+"' ,grade = '"+grade+"' where id = "+uid);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        binder = new MyBinder();
        return binder;
    }
}
