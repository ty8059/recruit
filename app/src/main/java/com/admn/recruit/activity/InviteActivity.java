package com.admn.recruit.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.presenter.InvitePresenter;
import com.admn.recruit.view.InviteView;

public class InviteActivity extends AppCompatActivity implements InviteView {

    private TextView tv_inviteMsg;

    private InvitePresenter invitePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        initView();
        invitePresenter = new InvitePresenter(this);
        SharedPreferences userSetting = getSharedPreferences("user", 0);
        Integer userId = userSetting.getInt("userId",0);
        invitePresenter.getInviteMsg(userId);
    }

    private void initView() {
        tv_inviteMsg = findViewById(R.id.tv_invite_msg);
    }

    @Override
    public void setInviteMsg(String msg) {
        tv_inviteMsg.setText(msg);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
