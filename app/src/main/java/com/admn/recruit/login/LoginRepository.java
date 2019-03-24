package com.admn.recruit.login;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface LoginRepository {

    @POST("/login/doLogin/")
    Call<LoginBean> login(@Query("username") String username, @Query("password") String password);
}
