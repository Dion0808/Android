package com.example.loginscreenhomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.Buffer;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button registerCancelButton;
    private Button registerButton;
    private Button sendButton;
    private EditText userName;
    private EditText passWord;
    private EditText passWordAgain;
    private EditText phoneNumber;
    private EditText verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerCancelButton=findViewById(R.id.bt_register_cancel);
        registerButton=findViewById(R.id.bt_register_register);
        sendButton=findViewById(R.id.bt_register_send);
        userName=findViewById(R.id.et_register_user);
        passWord=findViewById(R.id.et_register_password);
        passWordAgain=findViewById(R.id.et_register_password2);
        phoneNumber=findViewById(R.id.et_register_number);
        verificationCode=findViewById(R.id.et_register_captcha);
        registerCancelButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    //正则表达式
    @Override
    public void onClick(View v) {
        String user_Name=userName.getText().toString().trim();
        String pass_Word=passWord.getText().toString().trim();
        String pass_Word_Again=passWordAgain.getText().toString().trim();
        String phone_Number=phoneNumber.getText().toString().trim();
        String captcha=verificationCode.getText().toString().trim();
        StringBuffer promptMessage=new StringBuffer(); //创建了一个StringBuffer对象promptMessage
        int promptMessageNumber=0;    //统计错误提示信息个数
        String usernameRegex = "^[A-Z][A-Za-z0-9]{5,11}$";  //验证用户名（大写字母开头，6-12个字母数字）
        String passwordRegex = "^[a-z]{4,16}$";  //验证密码（包含6-20小写字母，长度为4-16位）
        String phoneRegex = "^1[0-9]{10}$";  //验证手机号码（中国大陆11位数字，以1开头）
        String verificationCodeRegex = "^[0-9]{4}$";  //验证验证码（4位数字）
        Pattern pattern_usernameRegex = Pattern.compile(usernameRegex);
        Pattern pattern_passwordRegex = Pattern.compile(passwordRegex);
        Pattern pattern_passwordAgainRegex = Pattern.compile(passwordRegex);
        Pattern pattern_phoneRegex = Pattern.compile(phoneRegex);
        Pattern pattern_verificationCodeRegex = Pattern.compile(verificationCodeRegex);
        Matcher matcher_usernameRegex = pattern_usernameRegex.matcher(user_Name);
        Matcher matcher_passwordRegex = pattern_passwordRegex.matcher(pass_Word);
        Matcher matcher_passwordAgainRegex = pattern_passwordAgainRegex.matcher(pass_Word_Again);
        Matcher matcher_phoneRegex = pattern_phoneRegex.matcher(phone_Number);
        Matcher matcher_verificationCodeRegex = pattern_verificationCodeRegex.matcher(captcha);
        //注册格式检查
        if(v.getId()==R.id.bt_register_cancel){
            Toast.makeText(RegisterActivity.this,"注册取消！",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        if(user_Name.length()>12||user_Name.length()<6){
            promptMessageNumber++;
            promptMessage.append("用户名长度不符合规则！\n");
            //Toast.makeText(RegisterActivity.this, "用户名长度不符合规则！", Toast.LENGTH_SHORT).show();
        }
        if (!matcher_usernameRegex.matches()) {
            promptMessageNumber++;
            promptMessage.append("用户名不符合规则！\n");
            //Toast.makeText(RegisterActivity.this, "用户名不符合规则！", Toast.LENGTH_SHORT).show();
        }
        if (pass_Word.length()>16||pass_Word.length()<4) {
            promptMessageNumber++;
            promptMessage.append("密码长度不符合规则！\n");
            //Toast.makeText(RegisterActivity.this, "密码长度不符合规则！", Toast.LENGTH_SHORT).show();
        }
        if (!matcher_passwordRegex.matches()) {
            promptMessageNumber++;
            promptMessage.append("密码不符合规则！\n");
            //Toast.makeText(RegisterActivity.this, "密码不符合规则！", Toast.LENGTH_SHORT).show();
        }
        if (!matcher_passwordAgainRegex.matches()) {
            promptMessageNumber++;
            promptMessage.append("确认密码不符合规则！\n");
            //Toast.makeText(RegisterActivity.this, "确认密码不符合规则！", Toast.LENGTH_SHORT).show();
        }
        if (!pass_Word.equals(pass_Word_Again)) {
            promptMessageNumber++;
            promptMessage.append("两次密码输入不一致！\n");
            //Toast.makeText(RegisterActivity.this, "两次密码输入不一致！", Toast.LENGTH_SHORT).show();
        }
        if (!matcher_phoneRegex.matches()) {
            promptMessageNumber++;
            promptMessage.append("手机号码不符合规则！\n");
            //Toast.makeText(RegisterActivity.this, "手机号码不符合规则！", Toast.LENGTH_SHORT).show();
        }

        if (!matcher_verificationCodeRegex.matches()) {
            promptMessageNumber++;
            promptMessage.append("验证码不符合规则！\n");
            //Toast.makeText(RegisterActivity.this, "验证码不符合规则！", Toast.LENGTH_SHORT).show();
        }
        if (!captcha.equals("1111")) {
            promptMessageNumber++;
            promptMessage.append("验证码不正确！\n");
            //Toast.makeText(RegisterActivity.this,"验证码不正确！",Toast.LENGTH_SHORT).show();
        }
        if (v.getId()==R.id.bt_register_send) {
            Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
        } else if (v.getId()==R.id.bt_register_register&&promptMessageNumber!=0) {
            Toast.makeText(RegisterActivity.this,promptMessage,Toast.LENGTH_SHORT).show();
        } else if (v.getId()==R.id.bt_register_register) {
            UserData.userName=user_Name;
            UserData.passWord=pass_Word;
            UserData.phoneNumber=phone_Number;
            Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}