package com.admn.recruit.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.adapter.PositionAppAdapter;
import com.admn.recruit.model.PositionApp;
import com.admn.recruit.presenter.PositionAppPresenter;
import com.admn.recruit.view.PositionAppView;

import java.util.ArrayList;
import java.util.List;

public class PositionAppActivity extends AppCompatActivity implements PositionAppView {

    private ListView recordListView;

    private PositionAppPresenter positionAppPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_app);
        recordListView = findViewById(R.id.lv_record);
        positionAppPresenter = new PositionAppPresenter(this);
        getPositionAppList();
    }


    private void getPositionAppList() {
        SharedPreferences userSetting = getSharedPreferences("user", 0);
        Integer userId = userSetting.getInt("userId",0);
        positionAppPresenter.getPositionAppList(userId);
    }


    @Override
    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initPositionAppList(List<PositionApp> positionAppList) {
        PositionAppAdapter positionAppAdapter = new PositionAppAdapter(PositionAppActivity.this, R.layout.position_app_item, positionAppList);
        recordListView.setAdapter(positionAppAdapter);
    }
}
