package com.admn.recruit.util;

import android.util.Log;

import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.model.SysCode;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private final static String TAG = "RetrofitUtil";
    public final static String BASE_URL = "http://king1314okok.cn:8090/";
    private static RetrofitUtil retrofitUtils;
    private final OkHttpClient okHttpClient;

    /**
     * 双重检验锁
     * @return
     */
    public static RetrofitUtil getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtil.class){
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtil();
                }
            }
        }
        return retrofitUtils;
    }


    private RetrofitUtil() {
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)//写入超时时间
                .readTimeout(5,TimeUnit.SECONDS)//读取超时时间
                .connectTimeout(5,TimeUnit.SECONDS)//超时时间
                // .addInterceptor(new HeaderInterceptor())//头部拦截器
                .build();
    }

    public <T> T createApi(Class<T> cls){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(cls);
    }

    public static SysCode findSysCodeByTypeCode(String typeCode) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        SysCodeRepository sysCodeService = retrofitUtil.createApi(SysCodeRepository.class);

        Call<ResultEntity> call = sysCodeService.findSysCodeByTypeCode(typeCode);
        SysCode sysCode = new SysCode();
        try {
            Object obj = call.execute().body();
            String data = new Gson().toJson(obj);
            Gson gson = new Gson();
            ResultEntity resultEntity = gson.fromJson(data, ResultEntity.class);
            if (resultEntity.isSuccess()) {
                String sysCodeStr = gson.toJson(resultEntity.getObj());
                sysCode = gson.fromJson(sysCodeStr, SysCode.class);
            }
        } catch (Exception e) {
            Log.e(TAG, "获取系统参数连接失败", e);
        }
        return sysCode;
    }
}
