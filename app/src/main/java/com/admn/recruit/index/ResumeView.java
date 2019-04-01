package com.admn.recruit.index;

import com.admn.recruit.model.Resume;

import java.util.List;

public interface ResumeView {

    void showMsg(String msg);

    void jumpToBasicInfo(Resume resume);

    void jumpToTarget(Resume resume);
}
