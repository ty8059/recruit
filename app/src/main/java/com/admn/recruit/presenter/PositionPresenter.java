package com.admn.recruit.presenter;

import com.admn.recruit.dao.PositionRepository;
import com.admn.recruit.model.Position;
import com.admn.recruit.model.ResultEntity;
import com.admn.recruit.model.Resume;
import com.admn.recruit.util.RetrofitUtil;
import com.admn.recruit.view.PositionView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PositionPresenter {

    private PositionView positionView;

    public PositionPresenter(PositionView positionView) {
        this.positionView = positionView;
    }

    public void getPositionList() {
        RetrofitUtil retrofitUtil = RetrofitUtil.getInstance();
        PositionRepository positionService = retrofitUtil.createApi(PositionRepository.class);
        Call<ResultEntity> call = positionService.getPositionList();
        call.enqueue(new Callback<ResultEntity>() {
            @Override
            public void onResponse(Call<ResultEntity> call, Response<ResultEntity> response) {
                Gson gson = new Gson();
                String responseStr = gson.toJson(response.body());
                ResultEntity resultEntity = gson.fromJson(responseStr, ResultEntity.class);
                if (resultEntity.isSuccess()) {
                    String listStr = gson.toJson(resultEntity.getObj());
                    JsonParser jsonParser = new JsonParser();
                    JsonArray jsonElements = jsonParser.parse(listStr).getAsJsonArray();
                    List<Position> list = new ArrayList<>();
                    for (JsonElement bean : jsonElements) {
                        Position position = gson.fromJson(bean, Position.class);//解析
                        list.add(position);
                    }
                    positionView.initPositionData(list);
                } else {
                    positionView.showMsg("获取职位表异常");
                }
            }

            @Override
            public void onFailure(Call<ResultEntity> call, Throwable t) {
                positionView.showMsg("网络异常");
            }
        });
    }
}
