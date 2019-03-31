package com.admn.recruit.login;

import com.admn.recruit.model.User;
import com.admn.recruit.util.RetrofitUtil;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public LoginView getiLoginView() {
        return this.loginView;
    }

    public void setiLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void doLogin(String username, String password) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        LoginRepository loginService = retrofitUtil.createApi(LoginRepository.class);

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
                System.out.println("结果为:" + success + "\nmsg为:" + msg + "\nobj为:" + data);

                if (success) {
                    // TODO 登录功能未屏蔽，待恢复
                    loginView.showMsg(msg);
                    loginView.setUserId(user);
                    loginView.jumpActivity();
                } else {
                    loginView.showMsg(msg);
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable throwable) {
                loginView.showMsg("连接失败");
            }
        });
    }

}
