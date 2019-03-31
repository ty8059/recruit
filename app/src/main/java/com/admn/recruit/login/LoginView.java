package com.admn.recruit.login;

import com.admn.recruit.model.User;
import com.admn.recruit.view.BaseView;

public interface LoginView extends BaseView {

    void setUserId(User user);
}
