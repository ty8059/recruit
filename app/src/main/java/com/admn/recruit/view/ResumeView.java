package com.admn.recruit.view;

import com.admn.recruit.model.Resume;

import java.util.List;

public interface ResumeView {

    void showMsg(String msg);

    void jumpToBasicInfo(Resume resume);

    void jumpToWorkExpEdit(Resume resume);

    void jumpToEduExpEdit(Resume resume);

    void jumpToTarget(Resume resume);
}
