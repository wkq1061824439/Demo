package com.example.asus.mysqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {

    private EditText username;
    private EditText usergrade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initText();
    }

    private void initText() {
        username = (EditText) findViewById(R.id.et_name);
        usergrade = (EditText) findViewById(R.id.et_grade);

        Intent intent = getIntent();
        String username = intent.getStringExtra("name");
        String usergrade = intent.getStringExtra("grade");
        this.username.setText(username);
        this.usergrade.setText(usergrade);
    }
}
