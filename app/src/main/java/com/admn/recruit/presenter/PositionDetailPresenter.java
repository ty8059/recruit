package com.admn.recruit.presenter;

import com.admn.recruit.dao.PositionRepository;
import com.admn.recruit.model.Position;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.util.RetrofitUtil;
import com.admn.recruit.view.PositionDetailView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositionDetailPresenter {

    private PositionDetailView positionDetailView;

    public PositionDetailPresenter(PositionDetailView positionDetailView) {
        this.positionDetailView = positionDetailView;
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
                positionDetailView.showMsg(resultEntity.getMsg());
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                positionDetailView.showMsg("网络异常");
            }
        });
    }
}
