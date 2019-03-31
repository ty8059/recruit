package com.admn.recruit.util;

import com.admn.recruit.model.ResultEntity;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SysCodeRepository {

    @POST("/getSysCode/")
    Call<ResultEntity> findSysCodeByTypeCode(@Query("typeCode") String typeCode);
}
