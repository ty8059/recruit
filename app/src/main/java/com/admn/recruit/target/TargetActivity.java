package com.admn.recruit.target;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.admn.recruit.R;

public class TargetActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_sex;
    private EditText et_birthday;
    private EditText et_tel;
    private EditText et_address;
    private EditText et_marital;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
    }
}
