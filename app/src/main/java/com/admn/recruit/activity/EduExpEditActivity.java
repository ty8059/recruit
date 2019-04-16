package com.admn.recruit.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.model.EduExp;
import com.admn.recruit.presenter.EduExpPresenter;
import com.admn.recruit.util.DateUtil;
import com.admn.recruit.view.EduExpView;

import java.util.Calendar;

public class EduExpEditActivity extends AppCompatActivity implements EduExpView, View.OnClickListener {

    private EditText et_degree;
    private EditText et_profession;
    private EditText et_eduStartTime;
    private EditText et_eduEndTime;
    private DatePicker dp_eduStartTime;
    private DatePicker dp_eduEndTime;
    private Button btn_setStartTime;
    private Button btn_setEndTime;
    private Button btn_submit;

    private EduExpPresenter eduExpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_exp_edit);
        initView();
        eduExpPresenter = new EduExpPresenter(this);
        btn_setStartTime.setOnClickListener(this);
        btn_setEndTime.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        SharedPreferences userSetting = getSharedPreferences("user", 0);
        Integer userId = userSetting.getInt("userId",0);
        eduExpPresenter.getEduExp(userId);
    }

    private void initView() {
        et_degree = findViewById(R.id.et_edu_background);
        et_profession = findViewById(R.id.et_profession);
        et_eduStartTime = findViewById(R.id.et_edu_start_time);
        et_eduEndTime = findViewById(R.id.et_edu_terminal_time);
        btn_setStartTime = findViewById(R.id.btn_set_edu_start_time);
        btn_setEndTime = findViewById(R.id.btn_set_edu_end_time);
        btn_submit = findViewById(R.id.btn_submit_edu_exp);
        dp_eduStartTime = findViewById(R.id.dp_edu_start_time);
        dp_eduEndTime = findViewById(R.id.dp_edu_end_time);
    }

    @Override
    public void setEduExpView(EduExp eduExp) {
        String startTime = eduExp.getStartTime().substring(0, 8);
        String endTime = eduExp.getEndTime().substring(0, 8);
        et_eduStartTime.setText(DateUtil.changeDateToHtml(startTime));
        et_eduEndTime.setText(DateUtil.changeDateToHtml(endTime));
        et_degree.setText(eduExp.getDegree());
        et_profession.setText(eduExp.getProfession());
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        SharedPreferences userSetting = getSharedPreferences("user", 0);
        Integer userId = userSetting.getInt("userId",0);
        switch (view.getId()) {
            case R.id.btn_submit_edu_exp:
                String startTime = et_eduStartTime.getText().toString();
                String endTime = et_eduEndTime.getText().toString();
                String degree = et_degree.getText().toString();
                String profession = et_profession.getText().toString();

                EduExp eduExp = new EduExp();
                eduExp.setUserId(userId);
                eduExp.setStartTime(DateUtil.changeDateToOracle(startTime) + "000000");
                eduExp.setEndTime(DateUtil.changeDateToOracle(endTime) + "000000");
                eduExp.setDegree(degree);
                eduExp.setProfession(profession);

                eduExpPresenter.editEduExp(eduExp);
                break;
            case R.id.btn_set_edu_start_time:
                startTimeDatePicker();
                break;
            case R.id.btn_set_edu_end_time:
                endTimeDatePicker();
                break;
            default:break;
        }
    }

    private void startTimeDatePicker() {
        dp_eduStartTime.setVisibility(View.VISIBLE);
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

        dp_eduStartTime.init(year, cal.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {

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
                dp_eduStartTime.setVisibility(View.INVISIBLE);
                et_eduStartTime.setText(DateUtil.changeDateToHtml(date));
            }
        });
    }

    private void endTimeDatePicker() {
        dp_eduEndTime.setVisibility(View.VISIBLE);
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

        dp_eduEndTime.init(year, cal.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {

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
                dp_eduEndTime.setVisibility(View.INVISIBLE);
                et_eduEndTime.setText(DateUtil.changeDateToHtml(date));
            }
        });
    }
}
