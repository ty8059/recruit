package com.admn.recruit.dao;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InviteRepository {

    @POST("/resume/getInviteMsg/")
    Call<ResultEntity> getInviteMsg(@Query("userId") Integer userId);
}
