package com.admn.recruit.presenter;

import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.model.User;
import com.admn.recruit.dao.RegisterRepository;
import com.admn.recruit.view.RegisterView;
import com.admn.recruit.util.RetrofitUtil;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    private RegisterView registerView;

    public RegisterPresenter(RegisterView registerView) {
        super();
        this.registerView = registerView;
    }

    public RegisterView getiRegisterView() {
        return this.registerView;
    }

    public void setiRegisterView(RegisterView registerView) {
        this.registerView = registerView;
    }

    public void doRegister(String username, String password, String userPhone) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        RegisterRepository registerService = retrofitUtil.createApi(RegisterRepository.class);

        Call<ResultEntity> call = registerService.register(username, password, userPhone);
        call.enqueue(new Callback<ResultEntity>() {

            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
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
            public void onFailure(Call<ResultEntity> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });
    }




}
