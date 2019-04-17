package com.admn.recruit.view;

import com.admn.recruit.model.Resume;

public interface TargetView {

    void setTargetView(Resume resume);

    void showMsg(String msg);
}
