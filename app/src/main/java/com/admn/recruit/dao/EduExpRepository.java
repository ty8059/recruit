package com.admn.recruit.dao;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EduExpRepository {

    @POST("/resume/editEduExp/")
    Call<ResultEntity> editEduExp(@Query("userId") Integer userId, @Query("startTime") String startTime, @Query("endTime") String endTime,
                                  @Query("degree") String degree, @Query("profession") String profession) ;

    @POST("/resume/getEduExpByUserId/")
    Call<ResultEntity> getEduExpByUserId(@Query("userId") Integer userId);
}
