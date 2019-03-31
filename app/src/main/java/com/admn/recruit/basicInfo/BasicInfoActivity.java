package com.admn.recruit.basicInfo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.admn.recruit.R;

public class BasicInfoActivity extends AppCompatActivity {


    private EditText et_name;
    private EditText et_sex;
    private EditText et_birthday;
    private EditText et_tel;
    private EditText et_address;
    private EditText et_marital;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);

        initView();
    }



    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_sex = findViewById(R.id.et_sex);
        et_birthday = findViewById(R.id.et_birthday);
        et_tel = findViewById(R.id.et_tel);
        et_address = findViewById(R.id.et_address);
        et_marital = findViewById(R.id.et_marital);
    }
}
