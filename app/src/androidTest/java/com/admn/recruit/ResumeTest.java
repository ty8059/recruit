package com.admn.recruit;

import android.support.test.runner.AndroidJUnit4;

import com.admn.recruit.index.ResumePresenter;
import com.admn.recruit.index.ResumeView;
import com.admn.recruit.model.Resume;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ResumeTest {

    private ResumePresenter resumePresenter = new ResumePresenter(new ResumeView() {

        @Override
        public void showMsg(String msg) {

        }

        @Override
        public void jumpToBasicInfo(Resume resume) {

        }

    });

    @Test
    public void resumeTest() {
        resumePresenter.getResume(3);
    }
}
