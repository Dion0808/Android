package com.example.loginscreenhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;//登录按钮
    private Button registerButton;//注册按钮
    private EditText phoneNumber;
    private EditText passWord;
    private ImageView isPwdShowIv;
    private boolean isHide = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=findViewById(R.id.bt_login_login);
        registerButton=findViewById(R.id.bt_login_register);
        phoneNumber=findViewById(R.id.et_phone_number);
        passWord=findViewById(R.id.et_password);
        isPwdShowIv = findViewById(R.id.login_is_miss_pwd);
        loginButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        isPwdShowIv.setOnClickListener(this);
        isPwdShowIv.setImageResource(R.drawable.show);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_login_login){
            submit();
        } else if (v.getId()==R.id.bt_login_register) {
            goRegister();
        } else if (v.getId()==R.id.login_is_miss_pwd) {
            isShowPassword();
        }
    }
    private void submit(){
        String phone_number=phoneNumber.getText().toString();
        String password=passWord.getText().toString();
        if (phone_number.equals(UserData.phoneNumber) && password.equals(UserData.passWord)) {
            Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("message", UserData.userName);
            setResult(RESULT_OK, intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
        }
    }
    private void goRegister(){
        Intent intent=new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
    private void isShowPassword() {
        if(isHide == false) {
            //显示密码的“眼睛”图标
            isPwdShowIv.setImageResource(R.drawable.show);
            //密文
            HideReturnsTransformationMethod method1 = HideReturnsTransformationMethod.getInstance();
            passWord.setTransformationMethod(method1);
            isHide = true;
        } else {
            //是表示隐藏密码的“眼睛”图标
            isPwdShowIv.setImageResource(R.drawable.miss);
            //密文
            TransformationMethod method2 = PasswordTransformationMethod.getInstance();
            passWord.setTransformationMethod(method2);
            isHide = false;
        }
        //重置光标位置
        int index = passWord.getText().toString().length();
        passWord.setSelection(index) ;
    }
}
