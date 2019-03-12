package com.admn.recruit.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.fragment.DataFragment;
import com.admn.recruit.fragment.DeviceFragment;
import com.admn.recruit.fragment.UploadFragment;
import com.admn.recruit.service.LoginService;
import com.admn.recruit.service.impl.LoginServiceImpl;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class IndexActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private String TAG = IndexActivity.class.getSimpleName();
    private FragmentTransaction transaction;
    private UploadFragment uploadFragment;
    private DataFragment dataFragment;
    private DeviceFragment deviceFragment;

    private LoginService loginService = new LoginServiceImpl();


    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setBottomNavigationBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.index_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor("#FFFFFF") //选中颜色
                .setInActiveColor("#DBDBDB") //未选中颜色
                .setBarBackgroundColor("#f0ffff");//导航栏背景色
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_upload_64px, "上传"))
                .addItem(new BottomNavigationItem(R.drawable.ic_upload_64px, "查看"))
                .addItem(new BottomNavigationItem(R.drawable.ic_upload_64px, "设备"))
                .setFirstSelectedPosition(0)
                .initialise(); //initialise 一定要放在 所有设置的最后一项
        setDefaultFragment();
    }

    /**
     * 设置默认导航栏
     */
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        uploadFragment = UploadFragment.newInstance("上传", "");
        transaction.replace(R.id.indexFragment, uploadFragment);
        transaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        setBottomNavigationBar();
        loginService.login("king", "111");
    }


    @Override
    public void onTabSelected(int position) {
        Log.d(TAG, "onTabSelected() called with: " + "position = [" + position + "]");
        FragmentManager fm = this.getSupportFragmentManager();
        // 开启事务
        transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (uploadFragment == null) {
                    uploadFragment = UploadFragment.newInstance("上传", "");
                    transaction.add(R.id.indexFragment, uploadFragment);
                } else {
                    transaction.show(uploadFragment);
                }
                dialog();
                break;
            case 1:
                if (dataFragment == null) {
                    dataFragment = dataFragment.newInstance("数据", "");
                    transaction.add(R.id.indexFragment, dataFragment);
                } else {
                    transaction.show(dataFragment);
                }
                break;
            case 2:
                if (deviceFragment == null) {
                    deviceFragment = deviceFragment.newInstance("设备", "");
                    transaction.add(R.id.indexFragment, deviceFragment);
                } else {
                    transaction.show(deviceFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();// 事务提交
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (uploadFragment != null) {
            transaction.hide(uploadFragment);
        }
        if (dataFragment != null) {
            transaction.hide(dataFragment);
        }
        if (deviceFragment != null) {
            transaction.hide(deviceFragment);
        }
    }

    private void dialog(

    ) {
        AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(IndexActivity.this);
        builder.setTitle("请回答");
        builder.setMessage("你觉得我好看吗？？");
        builder.setPositiveButton("当然是好看了！！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(IndexActivity.this, "嘻嘻嘻", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("我觉得一般", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(IndexActivity.this,"那你再瞅瞅~",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("我觉得不好看", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(IndexActivity.this,"嘤嘤嘤",Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}



