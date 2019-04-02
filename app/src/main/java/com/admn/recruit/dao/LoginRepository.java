package com.admn.recruit.dao;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface LoginRepository {

    @POST("/login/doLogin/")
    Call<ResultEntity> login(@Query("username") String username, @Query("password") String password);
}
