package com.admn.recruit.view;

import com.admn.recruit.model.PositionApp;

import java.util.List;

public interface PositionAppView {

    void showMsg(String msg);

    void initPositionAppList(List<PositionApp> positionAppList);
}
