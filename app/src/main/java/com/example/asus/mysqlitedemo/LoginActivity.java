package com.example.asus.mysqlitedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_login;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initButton();

    }

    private void initButton() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                Intent intent = new Intent(this,ListViewActivity.class);
                startActivity(intent);
                break;

        }

    }
}
