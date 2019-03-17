package com.admn.recruit.dao;

import com.admn.recruit.login.LoginBean;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface LoginRepository {

    @POST("doLogin/")
    Call<LoginBean> login(@Query("username") String username, @Query("password") String password);
}
