package com.admn.recruit.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.admn.recruit.fragment.DataFragment;
import com.admn.recruit.fragment.DeviceFragment;
import com.admn.recruit.fragment.UploadFragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.admn.recruit.R;

public class IndexActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private String TAG = IndexActivity.class.getSimpleName();
    private FragmentTransaction transaction;
    private UploadFragment uploadFragment;
    private DataFragment dataFragment;
    private DeviceFragment deviceFragment;

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setBottomNavigationBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.index_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor("#FF107FFD") //选中颜色
                .setInActiveColor("#e9e6e6") //未选中颜色
                .setBarBackgroundColor("#1ccbae");//导航栏背景色
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


    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}
