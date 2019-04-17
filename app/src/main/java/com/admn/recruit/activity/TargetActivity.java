package com.admn.recruit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.model.Resume;
import com.admn.recruit.presenter.TargetPresenter;
import com.admn.recruit.util.DateUtil;
import com.admn.recruit.view.TargetView;
import com.google.gson.Gson;

import java.util.Calendar;

public class TargetActivity extends AppCompatActivity implements View.OnClickListener, TargetView {

    private EditText et_area;
    private EditText et_position;
    private EditText et_salary_target;
    private EditText et_arrival_time;
    private DatePicker dp_arrival_time;
    private Button btn_setArrivalTime;
    private Button btn_submit;

    private Resume resume;

    private TargetPresenter targetPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        initView();
        firstSetResumeInfo();
        targetPresenter = new TargetPresenter(this);
    }

    private void initView() {
        et_area = findViewById(R.id.et_place);
        et_position = findViewById(R.id.et_position);
        et_salary_target = findViewById(R.id.et_salary_target);
        et_arrival_time = findViewById(R.id.et_arrival_time);
        dp_arrival_time = findViewById(R.id.dp_arrival_time);
        btn_setArrivalTime = findViewById(R.id.btn_set_arrival_time);
        btn_submit = findViewById(R.id.btn_submit_target);

        btn_setArrivalTime.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
    }

    private void firstSetResumeInfo() {
        String resumeJson = getIntent().getStringExtra("resumeJson");
        if (resumeJson != null) {
            Gson gson = new Gson();
            resume = gson.fromJson(resumeJson, Resume.class);
        }
        et_area.setText(resume.getArea());
        et_position.setText(resume.getPosition());
        et_salary_target.setText(Long.toString(resume.getTargetSalary()));
        String arrivalTime = resume.getArrivalTime().substring(0, 8);
        et_arrival_time.setText(DateUtil.changeDateToHtml(arrivalTime));
    }

    private void datePicker() {
        dp_arrival_time.setVisibility(View.VISIBLE);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit_target:
                String arrivalTime = et_arrival_time.getText().toString();
                String area = et_area.getText().toString();
                String position = et_position.getText().toString();
                Long targetSalary;
                try {
                    targetSalary = Long.parseLong(et_salary_target.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "请输入数字", Toast.LENGTH_SHORT).show();
                    return;
                }
                resume.setArrivalTime(DateUtil.changeDateToOracle(arrivalTime) + "000000");
                resume.setArea(area);
                resume.setPosition(position);
                resume.setTargetSalary(targetSalary);
                targetPresenter.editTarget(resume);
                break;
            case R.id.btn_set_arrival_time:
                datePicker();
                break;
            default: break;
        }
    }

    @Override
    public void setTargetView(Resume resume) {
        et_area.setText(resume.getArea());
        et_position.setText(resume.getPosition());
        et_salary_target.setText(Long.toString(resume.getTargetSalary()));
        String arrivalTime = resume.getArrivalTime().substring(0, 8);
        et_arrival_time.setText(DateUtil.changeDateToHtml(arrivalTime));
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
