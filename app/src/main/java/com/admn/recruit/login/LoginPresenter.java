package com.admn.recruit.login;

import com.admn.recruit.dao.LoginRepository;
import com.admn.recruit.model.User;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginPresenter {
    private ILoginView loginView;
    private String baseUrl = "http://king1314okok.cn/";

    public LoginPresenter(ILoginView loginView) {
        super();
        this.loginView = loginView;
    }

    public ILoginView getiLoginView() {
        return this.loginView;
    }

    public void setiLoginView(ILoginView loginView) {
        this.loginView = loginView;
    }

    public void doLogin(String username, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginRepository loginService = retrofit.create(LoginRepository.class);

        Call<LoginBean> call = loginService.login(username, password);
        call.enqueue(new Callback<LoginBean>() {

            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                boolean success = response.body().isSuccess();
                String msg = response.body().getMsg();
                Object obj = response.body().getObj();
                String data = new Gson().toJson(obj);
                Gson gson = new Gson();
                User user = gson.fromJson(data, User.class);
                System.out.println("结果为:" + success + "\nmsg为:" + "\nobj为:" + data);

                if (success) {
                    // TODO 登录功能未屏蔽，待恢复
                    loginView.showMsg(msg);
                    loginView.jumpActivity();
                } else {
                    loginView.showMsg(msg);
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }

}
