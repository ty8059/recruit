package com.admn.recruit.presenter;

import com.admn.recruit.dao.WorkExpRepository;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.model.WorkExp;
import com.admn.recruit.util.RetrofitUtil;
import com.admn.recruit.view.WorkExpView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkExpPresenter {

    private WorkExpView workExpView;

    public WorkExpPresenter(WorkExpView workExpView) {
        this.workExpView = workExpView;
    }

    public void getWorkExp(Integer userId) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        WorkExpRepository eduExpService = retrofitUtil.createApi(WorkExpRepository.class);
        Call<ResultEntity> call = eduExpService.getWorkExpByUserId(userId);
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                if (resultEntity.isSuccess()) {
                    String workExpJson = gson.toJson(resultEntity.getObj());
                    WorkExp workExp = gson.fromJson(workExpJson, WorkExp.class);
                    if (workExp != null) {
                        workExpView.setWorkExpView(workExp);
                    }
                } else {
                    workExpView.showMsg(resultEntity.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                workExpView.showMsg("网络异常");
            }
        });
    }

    public void editEduExp(WorkExp workExp) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        WorkExpRepository eduExpService = retrofitUtil.createApi(WorkExpRepository.class);
        Call<ResultEntity> call = eduExpService.editWorkExp(workExp.getUserId(), workExp.getStartTime(),
                workExp.getEndTime(), workExp.getCompany(), workExp.getPosition(), workExp.getDescription());
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                workExpView.showMsg(resultEntity.getMsg());
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                workExpView.showMsg("网络异常");
            }
        });
    }

}
