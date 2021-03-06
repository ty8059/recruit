package com.admn.recruit.presenter;


import com.admn.recruit.dao.ResumeRepository;
import com.admn.recruit.view.ResumeView;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.model.Resume;
import com.admn.recruit.util.RetrofitUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResumePresenter {

    private ResumeView resumeView;

    public ResumePresenter(ResumeView resumeView) {
        this.resumeView = resumeView;
    }

    public void getResume(Integer userId, final int jumpType) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        ResumeRepository resumeService = retrofitUtil.createApi(ResumeRepository.class);
        Call<ResultEntity> call = resumeService.getResumeByUserId(userId);
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                if (resultEntity.isSuccess()) {
                    String resumeListStr = gson.toJson(resultEntity.getObj());
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonElements = jsonParser.parse(resumeListStr).getAsJsonArray();
                    List<Resume> list = new ArrayList<>();
                    for (JsonElement bean : jsonElements) {
                        Resume resume = gson.fromJson(bean, Resume.class);//解析
                        list.add(resume);
                    }
                    if (list.size() == 0) {
                        switch (jumpType) {
                            case 0:
                                resumeView.jumpToBasicInfo(null);
                                break;
                            case 1:
                                resumeView.jumpToWorkExpEdit(null);
                                break;
                            case 2:
                                resumeView.jumpToEduExpEdit(null);
                                break;
                            case 3:
                                resumeView.jumpToTarget( null);
                                break;
                        }

                    } else {
                        switch (jumpType){
                            case 0:
                                resumeView.jumpToBasicInfo(list.get(0));
                                break;
                            case 1:
                                resumeView.jumpToWorkExpEdit(list.get(0));
                                break;
                            case 2:
                                resumeView.jumpToEduExpEdit(list.get(0));
                                break;
                            case 3:
                                resumeView.jumpToTarget(list.get(0));
                                break;
                        }
                    }
                } else {
                    resumeView.showMsg(resultEntity.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                resumeView.showMsg("连接失败");
            }
        });
    }


}
