package com.admn.recruit.presenter;

import com.admn.recruit.dao.EduExpRepository;
import com.admn.recruit.model.EduExp;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.util.RetrofitUtil;
import com.admn.recruit.view.EduExpView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EduExpPresenter {

    private EduExpView eduExpView;

    public EduExpPresenter(EduExpView eduExpView) {
        this.eduExpView = eduExpView;
    }

    public void getEduExp(Integer userId) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        EduExpRepository eduExpService = retrofitUtil.createApi(EduExpRepository.class);
        Call<ResultEntity> call = eduExpService.getEduExpByUserId(userId);
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                if (resultEntity.isSuccess()) {
                    String eduExpJson = gson.toJson(resultEntity.getObj());
                    EduExp eduExp = gson.fromJson(eduExpJson, EduExp.class);
                    if (eduExp != null) {
                        eduExpView.setEduExpView(eduExp);
                    }
                } else {
                    eduExpView.showMsg(resultEntity.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                eduExpView.showMsg("网络异常");
            }
        });
    }

    public void editEduExp(EduExp eduExp) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        EduExpRepository eduExpService = retrofitUtil.createApi(EduExpRepository.class);
        Call<ResultEntity> call = eduExpService.editEduExp(eduExp.getUserId(), eduExp.getStartTime(), eduExp.getEndTime(), eduExp.getDegree(),
                eduExp.getProfession());
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                eduExpView.showMsg(resultEntity.getMsg());
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                eduExpView.showMsg("网络异常");
            }
        });
    }

}
