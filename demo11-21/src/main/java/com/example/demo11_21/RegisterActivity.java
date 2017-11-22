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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText add_name;
    private EditText add_pwd;
    private Button add_submit;
    private Handler handler;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initEditText();
        initButton();
    }

    private void initButton() {
        add_submit = (Button) findViewById(R.id.add_submit);
        add_submit.setOnClickListener(this);
    }

    private void initEditText() {
        add_name = (EditText) findViewById(R.id.add_name);
        add_pwd = (EditText) findViewById(R.id.add_password);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_submit:
                String name = add_name.getText().toString();
                String pwd = add_pwd.getText().toString();
                url = "Http://10.101.1.46:8080/stu/add?name="+name+"&password="+pwd;
                Http();
                Handeler();
                break;
        }
    }

    private void Http(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http.http(url);
                handler.sendEmptyMessage(1);
            }
        }).start();
    }

    private void Handeler(){
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                switch (message.what){
                    case 1:
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}
