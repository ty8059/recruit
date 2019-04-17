package com.admn.recruit.presenter;

import com.admn.recruit.dao.ResumeRepository;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.model.Resume;
import com.admn.recruit.util.RetrofitUtil;
import com.admn.recruit.view.TargetView;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TargetPresenter {

    private TargetView targetView;

    public TargetPresenter(TargetView targetView) {
        this.targetView = targetView;
    }

    public void editTarget(Resume resume) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        ResumeRepository resumeService = retrofitUtil.createApi(ResumeRepository.class);
        Call<ResultEntity> call = resumeService.editTarget(resume.getUserId(), resume.getArea(),
                resume.getPosition(), resume.getTargetSalary(), resume.getArrivalTime());
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                targetView.showMsg(resultEntity.getMsg());
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                targetView.showMsg("网络异常");
            }
        });

    }

}
