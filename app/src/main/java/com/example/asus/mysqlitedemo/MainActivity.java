package com.example.asus.mysqlitedemo;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.asus.mysqlitedemo.adapter.MyAdapter;
import com.example.asus.mysqlitedemo.bean.User;
import com.example.asus.mysqlitedemo.provider.MyContentProvider;
import com.example.asus.mysqlitedemo.service.MyService;
import com.example.asus.mysqlitedemo.sqlite.MySqliteOpenHalper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MySqliteOpenHalper mySqlite;
    private SQLiteDatabase db;
    private ListView lv_show;
    private ArrayList<User> list = new ArrayList<>();
    private Button btn_del;
    private Button btn_add;
    private Button btn_update;
    private MyAdapter myAdapter;
    private EditText et_id;
    private EditText et_name;
    private EditText et_grade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSqlite();
        initAdapter();
        initListShow();
        initDel();
        initAdd();
        initUpdate();
    }

    private void initAdapter() {
        lv_show = (ListView) findViewById(R.id.lv_show);
        myAdapter = new MyAdapter(this,R.layout.listview_layout_item,list);
        lv_show.setAdapter(myAdapter);
    }

    private void initUpdate() {
        et_id = (EditText) findViewById(R.id.et_id);
        et_name = (EditText) findViewById(R.id.et_name);
        et_grade = (EditText) findViewById(R.id.et_grade);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                String name = et_name.getText().toString();
                String grade = et_grade.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("grade",grade);
                getContentResolver().update(
                        Uri.parse(MyContentProvider.CONTENT_URI_CONTACT),
                        values,
                        "id=?",
                        new String[] {id}
                );
                myAdapter.clear();
                initListShow();
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initAdd() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_grade = (EditText) findViewById(R.id.et_grade);
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString();
                String grade = et_grade.getText().toString();
                ContentValues values = new ContentValues();
                values.put("name",name);
                values.put("grade",grade);
                getContentResolver().insert(
                        Uri.parse(MyContentProvider.CONTENT_URI_CONTACT),values
                );
                myAdapter.clear();
                initListShow();
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initDel() {
        et_id = (EditText) findViewById(R.id.et_id);
        btn_del = (Button) findViewById(R.id.btn_del);
        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                getContentResolver().delete(
                        Uri.parse(MyContentProvider.CONTENT_URI_CONTACT),"id=?",new String[] {id}
                );
                myAdapter.clear();
                initListShow();
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initListShow() {
        ArrayList<User> lists = new ArrayList<>();
        Cursor cursor = getContentResolver().query(
            Uri.parse(MyContentProvider.CONTENT_URI_CONTACT),
                null,null,null,null
        );
        while(cursor.moveToNext()){
            User user = new User();
            Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String grade = cursor.getString(cursor.getColumnIndex("grade"));
            user.setId(id);
            user.setName(name);
            user.setGrade(grade);
            list.add(user);
        }
    }

    private void initSqlite() {
        mySqlite = new MySqliteOpenHalper(this,"db_user",null,1);
        db = mySqlite.getWritableDatabase();
    }

}
