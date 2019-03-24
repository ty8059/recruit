package com.admn.recruit.util;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

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

    public <T> T createApi(String baseUrl, Class<T> cls){
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(cls);
    }
}
