package com.admn.recruit.presenter;

import com.admn.recruit.dao.InviteRepository;
import com.admn.recruit.model.EduExp;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.util.RetrofitUtil;
import com.admn.recruit.view.InviteView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvitePresenter {

    private InviteView inviteView;

    public InvitePresenter(InviteView inviteView) {
        this.inviteView = inviteView;
    }

    public void getInviteMsg(Integer userId) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        InviteRepository inviteRepository = retrofitUtil.createApi(InviteRepository.class);
        Call<ResultEntity> call = inviteRepository.getInviteMsg(userId);
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                inviteView.setInviteMsg(resultEntity.getMsg());
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                inviteView.setInviteMsg("网络异常");
            }
        });
    }

}
