package com.example.demo11_21;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo11_21.util.Http;

import java.io.BufferedReader;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText login_name;
    private EditText login_pwd;
    private Button btn_login;
    private Button btn_register;
    private Handler handler;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initButton();
        initEditText();
    }

    private void initEditText() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_pwd = (EditText) findViewById(R.id.login_password);
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
                String name = login_name.getText().toString();
                String pwd = login_pwd.getText().toString();
                url = "Http://10.101.1.46:8080/stu/login?name="+name+"&password="+pwd;
                Http();
                Handler();
                break;
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                break;
        }
    }

    private void Handler(){
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what){
                    case 1:
                        initSuccess();
                        break;
                    case 2:
                        initFalse();
                        break;
                }
                return false;
            }
        });
    }

    private void initSuccess(){
        Toast.makeText(this, "登陆成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this,ListViewActivity.class));
    }

    private void initFalse(){
        Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
    }

    private void Http(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader br = Http.http(url);
                int i = Integer.parseInt(Http.getJSON(br));
                handler.sendEmptyMessage(i);
            }
        }).start();
    }
}
