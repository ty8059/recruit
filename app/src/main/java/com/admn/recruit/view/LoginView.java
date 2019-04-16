package com.admn.recruit.view;

import com.admn.recruit.model.User;

public interface LoginView {

    void showMsg(String msg);

    void setUserId(User user);

    void jumpActivity();
}
