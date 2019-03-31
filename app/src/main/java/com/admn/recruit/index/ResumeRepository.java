package com.admn.recruit.index;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ResumeRepository {

    @POST("/resume/addResume/")
    Call<ResultEntity> addResume(@Query("username") String username, @Query("password") String password);

    @POST("/resume/getResumeByUserId")
    Call<ResultEntity> getResumeByUserId(@Query("userId") Integer userId);
}
