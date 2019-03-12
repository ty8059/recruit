package com.admn.recruit.service.impl;

import android.util.Log;

import com.admn.recruit.service.LoginService;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginServiceImpl implements LoginService {


    @Override
    public void login(String username, String password) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("username",username);
        formBody.add("password", password);
        Request request = new Request.Builder()
                .url("http://king1314okok.cn/doLogin")
                .post(formBody.build())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("response", "连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("response", "返回为: " + result);

                boolean success;
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(result);
                    success = jsonObject.getBoolean("success");
                    Log.i("response", "是否成功: " + success);
                } catch (JSONException e) {
                    Log.e("json", "json转换失败");
                }
            }
        });
    }

}
