package com.admn.recruit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.presenter.RegisterPresenter;
import com.admn.recruit.view.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    private Button registerBtn;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etUserPhone;

    private RegisterPresenter registerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPresenter = new RegisterPresenter(this);
        initView();

        // 登录按钮的点击事件
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行登录操作
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                String userPhone = etUserPhone.getText().toString();
                registerPresenter.doRegister(username, password, userPhone);
            }
        });
    }

    private void initView() {
        registerBtn = findViewById(R.id.btn_register);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etUserPhone = findViewById(R.id.et_user_phone);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void jumpActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
