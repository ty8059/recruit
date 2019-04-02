package com.admn.recruit.register;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RegisterRepository {

    @POST("/register/doRegister/")
    Call<ResultEntity> register(@Query("username") String username, @Query("password") String password, @Query("userPhone") String uerPhone);

}
