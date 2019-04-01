package com.admn.recruit.basicinfo;

import com.admn.recruit.index.ResumeRepository;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.model.Resume;
import com.admn.recruit.util.RetrofitUtil;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasicInfoPresenter {

    private BasicInfoView basicInfoView;

    public BasicInfoPresenter(BasicInfoView basicInfoView) {
        this.basicInfoView = basicInfoView;
    }

    public void editResume(Resume resume) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        ResumeRepository resumeService = retrofitUtil.createApi(ResumeRepository.class);
        Call<ResultEntity> call = resumeService.addResume(resume.getUserId(), resume.getRealName(), resume.getSex(), resume.getBirthday(),
                resume.getUserPhone(), resume.getAddress(), resume.getMarriage());
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                if (resultEntity.isSuccess()) {
                    basicInfoView.showMsg(resultEntity.getMsg());
                } else {
                    basicInfoView.showMsg(resultEntity.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                basicInfoView.showMsg("网络异常");
            }
        });
    }

}
