package com.admn.recruit.presenter;

import com.admn.recruit.dao.PositionRepository;
import com.admn.recruit.model.PositionApp;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.util.RetrofitUtil;
import com.admn.recruit.view.PositionAppView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositionAppPresenter {

    private PositionAppView positionAppView;

    public PositionAppPresenter(PositionAppView positionAppView) {
        this.positionAppView = positionAppView;
    }

    public void applyPosition(Integer positionId, Integer userId) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        PositionRepository positionService = retrofitUtil.createApi(PositionRepository.class);
        Call<ResultEntity> call = positionService.applyPosition(positionId, userId);
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                positionAppView.showMsg(resultEntity.getMsg());
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                positionAppView.showMsg("网络异常");
            }
        });
    }

    public void getPositionAppList(Integer userId) {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        PositionRepository positionService = retrofitUtil.createApi(PositionRepository.class);
        Call<ResultEntity> call = positionService.getPositionAppByUserId(userId);
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                if (resultEntity.isSuccess()) {
                    String appListStr = gson.toJson(resultEntity.getObj());
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonElements = jsonParser.parse(appListStr).getAsJsonArray();
                    List<PositionApp> list = new ArrayList<>();
                    for (JsonElement bean : jsonElements) {
                        PositionApp positionApp = gson.fromJson(bean, PositionApp.class);
                        list.add(positionApp);
                    }
                    positionAppView.initPositionAppList(list);
                } else {
                    positionAppView.showMsg(resultEntity.getMsg());
                }
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                positionAppView.showMsg("网络异常");
            }
        });
    }
}
