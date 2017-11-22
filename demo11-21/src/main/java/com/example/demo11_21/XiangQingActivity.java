package com.example.demo11_21;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class XiangQingActivity extends AppCompatActivity {
    private TextView xiangid;
    private TextView xiangname;
    private TextView xiangpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_qing);
        Intent intent=this.getIntent();
        this.xiangid= (TextView) findViewById(R.id.xiang_id);
        this.xiangname= (TextView) findViewById(R.id.xiang_name);
        this.xiangpassword= (TextView) findViewById(R.id.xiang_password);
        xiangid.setText(intent.getStringExtra("id"));
        xiangname.setText(intent.getStringExtra("name"));
        xiangpassword.setText(intent.getStringExtra("password"));
    }
}
