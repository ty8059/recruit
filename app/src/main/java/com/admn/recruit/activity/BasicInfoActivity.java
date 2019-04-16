package com.admn.recruit.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.presenter.BasicInfoPresenter;
import com.admn.recruit.view.BasicInfoView;
import com.admn.recruit.model.Resume;
import com.admn.recruit.util.DateUtil;
import com.google.gson.Gson;

import java.util.Calendar;

public class BasicInfoActivity extends AppCompatActivity implements BasicInfoView, View.OnClickListener {

    private EditText et_name;
    private EditText et_sex;
    private EditText et_birthday;
    private EditText et_tel;
    private EditText et_email;
    private EditText et_address;
    private EditText et_marital;
    private DatePicker dp_birthday;
    private Button btn_birthday;
    private Button btn_basicInfoSubmit;

    private Resume resume;
    private BasicInfoPresenter basicInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_info);
        getResumeJson();
        initView();
        setResumeInfo();
        btn_birthday.setOnClickListener(this);
        btn_basicInfoSubmit.setOnClickListener(this);
    }

    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_sex = findViewById(R.id.et_sex);
        et_birthday = findViewById(R.id.et_birthday);
        et_tel = findViewById(R.id.et_tel);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
        et_marital = findViewById(R.id.et_marital);
        dp_birthday = findViewById(R.id.dp_birthday);
        btn_birthday = findViewById(R.id.btn_set_birthday);
        btn_basicInfoSubmit = findViewById(R.id.btn_basic_info_submit);
    }

    private void setResumeInfo() {
        if (resume != null) {
            et_name.setText(resume.getRealName());
            if (resume.getSex().equals("00")){
                et_sex.setText("男");
            } else {
                et_sex.setText("女");
            }
            String time = resume.getBirthday().substring(0, 8);
            et_birthday.setText(DateUtil.changeDateToHtml(time));
            et_tel.setText(resume.getUserPhone());
            et_email.setText(resume.getReserved1());
            et_address.setText(resume.getAddress());

            if (resume.getMarriage().equals("00")) {
                et_marital.setText("未婚");
            } else {
                et_marital.setText("已婚");
            }
        }
    }

    private void getResumeJson() {
        String resumeJson = getIntent().getStringExtra("resumeJson");
        if (resumeJson != null) {
            Gson gson = new Gson();
            resume =  gson.fromJson(resumeJson, Resume.class);
        }
    }

    private void datePicker() {
        Calendar cal;//显示当前日期
        int year;
        int month;
        int day;
        int hour;
        int minute;

        //获取日历的对象
        cal=Calendar.getInstance();
        //获取年月日时分秒信息
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH) + 1;//注意点 ，要加一
        day = cal.get(Calendar.DAY_OF_MONTH);
        hour = cal.get(Calendar.HOUR_OF_DAY);
        minute = cal.get(Calendar.MINUTE);

        dp_birthday.init(year, cal.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {

            @Override
            public void onDateChanged(DatePicker view, int year, int month, int day) {
                month++;
                String monthStr;
                String dayStr;
                if (month < 10) {
                    monthStr = "0" + Integer.toString(month);
                } else {
                    monthStr = Integer.toString(month);
                }
                if (day < 10) {
                    dayStr = "0" + Integer.toString(day);
                } else {
                    dayStr = Integer.toString(day);
                }
                String date = Integer.toString(year) + monthStr + dayStr;

                dp_birthday.setVisibility(View.INVISIBLE);
                et_birthday.setText(DateUtil.changeDateToHtml(date));
            }
        });
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_basic_info_submit:
                basicInfoPresenter = new BasicInfoPresenter(BasicInfoActivity.this);
                SharedPreferences userSetting = getSharedPreferences("user", 0);
                Integer userId = userSetting.getInt("userId",0);
                Resume resume = new Resume();
                resume.setUserId(userId);
                resume.setRealName(et_name.getText().toString());
                String sex = et_sex.getText().toString();
                if (sex.equals("男")) {
                    resume.setSex("00");
                } else {
                    resume.setSex("01");
                }
                String marriage = et_marital.getText().toString();
                if (marriage.equals("未婚")) {
                    resume.setMarriage("00");
                } else {
                    resume.setMarriage("01");
                }
                String date = et_birthday.getText().toString();
                resume.setBirthday(DateUtil.changeDateToOracle(date) + "000000");
                resume.setUserPhone(et_tel.getText().toString());
                resume.setReserved1(et_email.getText().toString());
                resume.setAddress(et_address.getText().toString());
                basicInfoPresenter.editResume(resume);
                break;
            case R.id.btn_set_birthday:
                dp_birthday.setVisibility(View.VISIBLE);
                datePicker();
                break;
            default: break;
        }
    }
}
