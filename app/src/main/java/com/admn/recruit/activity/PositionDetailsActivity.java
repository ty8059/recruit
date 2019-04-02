package com.admn.recruit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.admn.recruit.R;
import com.admn.recruit.model.Position;
import com.google.gson.Gson;

import java.math.BigDecimal;

public class PositionDetailsActivity extends AppCompatActivity {

    private TextView positionName;
    private TextView positionType;
    private TextView positionSalary;
    private TextView salaryType;
    private TextView eduRequirement;
    private TextView workExpRequirement;
    private TextView workArea;
    private TextView workDesc;

    private Position position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_details);
        initView();
        getPositionJson();
        setPositionInfo();
    }

    private void initView() {
        positionName = findViewById(R.id.tv_position_name);
        positionType = findViewById(R.id.tv_position_type);
        positionSalary = findViewById(R.id.tv_position_salary);
        salaryType = findViewById(R.id.tv_salary_type);
        eduRequirement = findViewById(R.id.tv_edu_requirement);
        workExpRequirement = findViewById(R.id.tv_work_exp_requirement);
        workArea = findViewById(R.id.tv_work_area);
        workDesc = findViewById(R.id.tv_work_description);
    }

    private void setPositionInfo() {
        positionName.setText(position.getPositionName());
        switch (position.getWorkType()) {
            case "00":
                positionType.setText("全职");
                break;
            case "01":
                positionType.setText("兼职");
                break;
            case "02":
                positionType.setText("实习");
                break;
            default: break;
        }
        switch (position.getSalaryType()) {
            case "00":
                salaryType.setText("月薪");
                break;
            case "01":
                salaryType.setText("日薪");
                break;
            default: break;
        }
        positionSalary.setText(position.getSalary().toString());
        eduRequirement.setText(position.getEduRequirement());
        workExpRequirement.setText(position.getWorkRequirement());
        workArea.setText(position.getWorkArea());
        workDesc.setText(position.getWorkDescription());
    }

    private void getPositionJson() {
        String positionJson = getIntent().getStringExtra("positionJson");
        if (positionJson != null) {
            Gson gson = new Gson();
            this.position =  gson.fromJson(positionJson, Position.class);
        }
    }
}
