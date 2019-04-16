package com.admn.recruit.dao;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PositionRepository {

    @POST("/position/getPositionList/")
    Call<ResultEntity> getPositionList();

    @POST("/positionApp/applyPosition")
    Call<ResultEntity> applyPosition(@Query("positionId") Integer positionId, @Query("userId") Integer userId);
}
