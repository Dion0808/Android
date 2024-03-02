package com.example.loginscreenhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 111;
    private int loginStatus =0;
    private Button loginButton;
    private TextView textView;
    private ImageButton userSitting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton=findViewById(R.id.bt_main_login);
        userSitting=findViewById(R.id.iv_user_sitting);
        textView = findViewById(R.id.tv_main_display);
        loginButton.setOnClickListener(this);
        userSitting.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        findViewById(R.id.bt_main_login).setOnClickListener(this);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String message = data.getStringExtra("message");
            textView.setText(message);
            loginStatus=1;
            loginButton.setText("退出");
        }
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_main_login){
            if(loginStatus==0){
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_CODE);
            } else if (loginStatus==1) {
                loginButton.setText("登录");
                textView.setText("尚未登录");
                loginStatus=0;
            }
        } else if (v.getId()==R.id.iv_user_sitting) {
                Intent intent = new Intent(this, UserSettingsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
        }
    }

}