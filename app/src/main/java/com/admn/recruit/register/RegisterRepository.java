package com.admn.recruit.register;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface RegisterRepository {

    @POST("/register/doRegister/")
    Call<RegisterBean> register(@Query("username") String username, @Query("password") String password, @Query("userPhone") String uerPhone);

}
