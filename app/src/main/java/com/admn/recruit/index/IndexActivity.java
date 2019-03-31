package com.admn.recruit.index;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.admn.recruit.R;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

public class IndexActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    private FragmentTransaction transaction;
    private IndexFragment indexFragment;
    private ResumeFragment resumeFragment;
    private PositionFragment positionFragment;

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setBottomNavigationBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.index_navigation_bar);
        bottomNavigationBar.setTabSelectedListener(this)
                .setMode(BottomNavigationBar.MODE_DEFAULT)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT)
                .setActiveColor("#FF8C00") //选中颜色
                .setInActiveColor("#DBDBDB") //未选中颜色
                .setBarBackgroundColor("#f0ffff");//导航栏背景色
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_upload_64px, "首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_upload_64px, "岗位"))
                .addItem(new BottomNavigationItem(R.drawable.ic_upload_64px, "简历"))
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
        indexFragment = IndexFragment.newInstance("首页", "");
        transaction.replace(R.id.indexFragment, indexFragment);
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
        FragmentManager fm = this.getSupportFragmentManager();
        // 开启事务
        transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (indexFragment == null) {
                    indexFragment = IndexFragment.newInstance("首页", "");
                    transaction.add(R.id.indexFragment, indexFragment);
                } else {
                    transaction.show(indexFragment);
                }
                break;

            case 1:
                if (positionFragment == null) {
                    positionFragment = PositionFragment.newInstance("岗位", "");
                    transaction.add(R.id.indexFragment, positionFragment);
                } else {
                    transaction.show(positionFragment);
                }
                break;

            case 2:
                if (resumeFragment == null) {
                    resumeFragment = ResumeFragment.newInstance("简历", "");
                    transaction.add(R.id.indexFragment, resumeFragment);
                } else {
                    transaction.show(resumeFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();// 事务提交
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (indexFragment != null) {
            transaction.hide(indexFragment);
        }
        if (positionFragment != null) {
            transaction.hide(positionFragment);
        }
        if (resumeFragment != null) {
            transaction.hide(resumeFragment);
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

}



