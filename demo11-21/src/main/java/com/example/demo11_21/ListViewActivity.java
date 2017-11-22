package com.example.demo11_21;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.demo11_21.adapter.MyAdapter;
import com.example.demo11_21.bean.User;
import com.example.demo11_21.util.Http;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private Handler handler;
    private MyAdapter myAdapter;
    private ArrayList<User> list = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        initHttp();
        initHandler();
    }

    private void initHandler() {
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what){
                    case 1:
                        initList();
                        break;
                }
                return false;
            }
        });
    }

    private void initHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = Http.jsonContact(Http.getJSON(Http.http(Http.start_contact)));
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    private void initList(){
        listView = (ListView) findViewById(R.id.lv_show);
        myAdapter = new MyAdapter(ListViewActivity.this,R.layout.list_view_layout_item,list);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListViewActivity.this,XiangQingActivity.class);
                intent.putExtra("id",list.get(i).getId()+"");
                intent.putExtra("name",list.get(i).getName());
                intent.putExtra("password",list.get(i).getPassword());
                startActivityForResult(intent,100);
            }
        });
    }
}
