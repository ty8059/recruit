package com.admn.recruit.register;

import com.admn.recruit.model.User;
import com.admn.recruit.util.RetrofitUtil;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    private IRegisterView registerView;

    private String baseUrl = "http://192.168.199.75:8080/";


    public RegisterPresenter(IRegisterView registerView) {
        super();
        this.registerView = registerView;
    }

    public IRegisterView getiRegisterView() {
        return this.registerView;
    }

    public void setiRegisterView(IRegisterView registerView) {
        this.registerView = registerView;
    }

    public void doRegister(String username, String password, String userPhone) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        RegisterRepository registerService = retrofitUtil.createApi(baseUrl, RegisterRepository.class);

        Call<RegisterBean> call = registerService.register(username, password, userPhone);
        call.enqueue(new Callback<RegisterBean>() {

            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                boolean success = response.body().isSuccess();
                String msg = response.body().getMsg();
                Object obj = response.body().getObj();
                String data = new Gson().toJson(obj);
                Gson gson = new Gson();
                User user = gson.fromJson(data, User.class);
                System.out.println("结果为:" + success + "\nmsg为:" + msg + "\nobj为:" + data);

                if (success) {
                    // TODO 登录功能未屏蔽，待恢复
                    registerView.showMsg(msg);
                    registerView.jumpActivity();
                } else {
                    registerView.showMsg(msg);
                }
            }

            @Override
            public void onFailure(Call<RegisterBean> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }




}
