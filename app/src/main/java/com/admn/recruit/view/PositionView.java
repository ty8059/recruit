package com.admn.recruit.view;

import com.admn.recruit.model.Position;

import java.util.List;

public interface PositionView {

    void initPositionData(List<Position> positionList);

    void showMsg(String msg);
}
