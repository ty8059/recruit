package com.admn.recruit.dao;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WorkExpRepository {

    @POST("/resume/editWorkExp/")
    Call<ResultEntity> editWorkExp(@Query("userId") Integer userId, @Query("startTime") String startTime, @Query("endTime") String endTime,
                                  @Query("company") String company, @Query("position") String position, @Query("description") String description) ;

    @POST("/resume/getWorkExpByUserId/")
    Call<ResultEntity> getWorkExpByUserId(@Query("userId") Integer userId);
}
