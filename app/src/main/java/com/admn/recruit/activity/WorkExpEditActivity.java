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
import com.admn.recruit.model.WorkExp;
import com.admn.recruit.presenter.WorkExpPresenter;
import com.admn.recruit.util.DateUtil;
import com.admn.recruit.view.WorkExpView;

import java.util.Calendar;

public class WorkExpEditActivity extends AppCompatActivity implements WorkExpView, View.OnClickListener {

    private EditText et_company;
    private EditText et_position;
    private EditText et_description;
    private EditText et_startTime;
    private EditText et_endTime;
    private DatePicker dp_workStartTime;
    private DatePicker dp_workEndTime;
    private Button btn_setStartTime;
    private Button btn_setEndTime;
    private Button btn_submit;

    private WorkExpPresenter workExpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_exp_edit);
        initView();

        workExpPresenter = new WorkExpPresenter(this);
        SharedPreferences userSetting = getSharedPreferences("user", 0);
        Integer userId = userSetting.getInt("userId",0);
        workExpPresenter.getWorkExp(userId);
    }

    private void initView() {
        et_startTime = findViewById(R.id.et_work_start_time);
        et_endTime = findViewById(R.id.et_work_end_time);
        et_company = findViewById(R.id.et_work_company);
        et_position = findViewById(R.id.et_work_position);
        et_description = findViewById(R.id.et_work_description);
        dp_workStartTime = findViewById(R.id.dp_work_start_time);
        dp_workEndTime = findViewById(R.id.dp_work_end_time);
        btn_setStartTime = findViewById(R.id.btn_set_work_start_time);
        btn_setEndTime = findViewById(R.id.btn_set_work_end_time);
        btn_submit = findViewById(R.id.btn_submit_work_exp);

        btn_setStartTime.setOnClickListener(this);
        btn_setEndTime.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    @Override
    public void setWorkExpView(WorkExp workExp) {
        String startTime = workExp.getStartTime().substring(0, 8);
        String endTime = workExp.getEndTime().substring(0, 8);
        et_startTime.setText(DateUtil.changeDateToHtml(startTime));
        et_endTime.setText(DateUtil.changeDateToHtml(endTime));
        et_company.setText(workExp.getCompany());
        et_position.setText(workExp.getPosition());
        et_description.setText(workExp.getDescription());
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
            case R.id.btn_submit_work_exp:
                String startTime = et_startTime.getText().toString();
                String endTime = et_endTime.getText().toString();
                String company = et_company.getText().toString();
                String position = et_position.getText().toString();
                String description = et_description.getText().toString();

                WorkExp workExp = new WorkExp();
                workExp.setUserId(userId);
                workExp.setStartTime(DateUtil.changeDateToOracle(startTime) + "000000");
                workExp.setEndTime(DateUtil.changeDateToOracle(endTime) + "000000");
                workExp.setCompany(company);
                workExp.setPosition(position);
                workExp.setDescription(description);
                workExpPresenter.editEduExp(workExp);
                break;
            case R.id.btn_set_work_start_time:
                startTimeDatePicker();
                break;
            case R.id.btn_set_work_end_time:
                endTimeDatePicker();
                break;
            default: break;
        }
    }

    private void startTimeDatePicker() {
        dp_workStartTime.setVisibility(View.VISIBLE);
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

        dp_workStartTime.init(year, cal.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {

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
                dp_workStartTime.setVisibility(View.INVISIBLE);
                et_startTime.setText(DateUtil.changeDateToHtml(date));
            }
        });
    }

    private void endTimeDatePicker() {
        dp_workEndTime.setVisibility(View.VISIBLE);
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

        dp_workEndTime.init(year, cal.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {

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
                dp_workEndTime.setVisibility(View.INVISIBLE);
                et_endTime.setText(DateUtil.changeDateToHtml(date));
            }
        });
    }
}
