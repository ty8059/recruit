package com.admn.recruit.dao;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;

public interface PositionRepository {

    @POST("/position/getPositionList/")
    Call<ResultEntity> getPositionList();
}
