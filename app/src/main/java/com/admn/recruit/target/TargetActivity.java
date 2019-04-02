package com.admn.recruit.target;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.admn.recruit.R;
import com.admn.recruit.model.Resume;
import com.admn.recruit.util.DateUtil;
import com.google.gson.Gson;

import java.util.Calendar;

public class TargetActivity extends AppCompatActivity {

    private EditText et_place;
    private EditText et_position;
    private EditText et_salary_type;
    private EditText et_salary_target;
    private EditText et_work_type;
    private EditText et_arrival_time;
    private DatePicker dp_arrival_time;

    private Resume resume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        initView();
        getResume();
        setResumeInfo();
    }

    private void initView() {
        et_place = findViewById(R.id.et_place);
        et_position = findViewById(R.id.et_position);
        et_salary_type = findViewById(R.id.et_salary_type);
        et_salary_target = findViewById(R.id.et_salary_target);
        et_work_type = findViewById(R.id.et_work_type);
        et_arrival_time = findViewById(R.id.et_arrival_time);
    }

    private void setResumeInfo() {
        et_place.setText(resume.getArea());
        et_position.setText(resume.getPosition());
        et_salary_type.setText(resume.getSalaryType());
        et_salary_target.setText(Long.toString(resume.getTargetSalary()));
        et_work_type.setText(resume.getWorkType());
        et_arrival_time.setText(resume.getArrivalTime());
    }

    private void getResume() {
        String resumeJson = getIntent().getStringExtra("resumeJson");
        if (resumeJson != null) {
            Gson gson = new Gson();
            resume = gson.fromJson(resumeJson, Resume.class);
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

        dp_arrival_time.init(year, cal.get(Calendar.MONTH), day, new DatePicker.OnDateChangedListener() {

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

                dp_arrival_time.setVisibility(View.INVISIBLE);
                et_arrival_time.setText(DateUtil.changeDateToHtml(date));
            }
        });
    }
}
