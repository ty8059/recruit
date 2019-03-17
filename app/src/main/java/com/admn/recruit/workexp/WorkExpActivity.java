package com.admn.recruit.workexp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.admn.recruit.R;

public class WorkExpActivity extends AppCompatActivity {

    private View view;
    private Button btn_add_work_exp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_exp);

        initView();

        btn_add_work_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(WorkExpEditActivity.class);
            }
        });
    }









    private void initView() {
        btn_add_work_exp =(Button) findViewById(R.id.btn_add_work_exp);

    }



    public void jumpActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

}
