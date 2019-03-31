package com.admn.recruit.applicationrecord;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.admn.recruit.R;
import com.admn.recruit.common.Constants;

import java.util.ArrayList;
import java.util.List;

public class ApplicationRecordActivity extends AppCompatActivity {

    private ListView recordListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_record);

        recordListView = (ListView) findViewById(R.id.lv_record);

        List<ApplicationRecord> recordList = initRecordData();
        ApplicationRecordAdapter recordAdapter = new ApplicationRecordAdapter(ApplicationRecordActivity.this, R.layout.application_record_item, recordList);
        recordListView.setAdapter(recordAdapter);
    }


    private List<ApplicationRecord> initRecordData() {
        List<ApplicationRecord> recordList = new ArrayList<>();

        ApplicationRecord record1 = new ApplicationRecord();

        record1.setPositionName("IT工程师");
        record1.setPositionSalary(10000L);
        record1.setPositionWorkType(Constants.WORK_TYPE.FULL_TIME);
        record1.setPositionIsPassed(Constants.AUDITING_STATE.PASSED);
        record1.setSalaryType(Constants.SALARY_TYPE.MONTH);

        recordList.add(record1);

        record1 = new ApplicationRecord();

        record1.setPositionName(".NET工程师");
        record1.setPositionSalary(100L);
        record1.setPositionWorkType(Constants.WORK_TYPE.PRACTICE);
        record1.setPositionIsPassed(Constants.AUDITING_STATE.PROCESSING);
        record1.setSalaryType(Constants.SALARY_TYPE.DAY);

        recordList.add(record1);

        return recordList;
    }


}
