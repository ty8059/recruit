package com.admn.recruit;

import android.support.test.runner.AndroidJUnit4;

import com.admn.recruit.model.SysCode;
import com.admn.recruit.util.RetrofitUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SysCodeTest {

    @Test
    public void sysCodeTest() {
        SysCode sysCode = RetrofitUtil.findSysCodeByTypeCode("USER_MARRIAGE:FALSE");
        System.out.println(sysCode.getDisplay1());
    }
}
