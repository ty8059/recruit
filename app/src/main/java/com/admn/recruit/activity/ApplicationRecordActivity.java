package com.admn.recruit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.admn.recruit.R;
import com.admn.recruit.adapter.ApplicationRecordAdapter;
import com.admn.recruit.model.ApplicationRecord;

import java.util.ArrayList;
import java.util.List;

public class ApplicationRecordActivity extends AppCompatActivity {

    private ListView recordListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_record);

        recordListView = findViewById(R.id.lv_record);

        List<ApplicationRecord> recordList = initRecordData();
        ApplicationRecordAdapter recordAdapter = new ApplicationRecordAdapter(ApplicationRecordActivity.this, R.layout.application_record_item, recordList);
        recordListView.setAdapter(recordAdapter);
    }


    private List<ApplicationRecord> initRecordData() {
        List<ApplicationRecord> recordList = new ArrayList<>();
        return recordList;
    }


}
