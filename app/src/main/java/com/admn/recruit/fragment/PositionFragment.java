package com.admn.recruit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.activity.PositionDetailsActivity;
import com.admn.recruit.adapter.PositionAdapter;
import com.admn.recruit.model.Position;
import com.admn.recruit.presenter.PositionPresenter;
import com.admn.recruit.view.PositionView;
import com.google.gson.Gson;

import java.util.List;

public class PositionFragment extends Fragment implements ListView.OnItemClickListener, PositionView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView positionListView;
    private View view;
    private List<Position> positionList;

    private String mParam1;
    private String mParam2;

    private PositionPresenter positionPresenter;

    public static PositionFragment newInstance(String param1, String param2) {
        PositionFragment fragment = new PositionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_position, container, false);
        positionListView =  view.findViewById(R.id.lv_position);
        positionPresenter = new PositionPresenter(this);
        positionPresenter.getPositionList();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void initPositionData(List<Position> positionList) {
        // 获取职位表
        this.positionList = positionList;
        PositionAdapter positionAdapter = new PositionAdapter(getActivity(), R.layout.position_item, positionList);
        positionListView.setAdapter(positionAdapter);
        positionListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Position positionBean = positionList.get(position);
        Intent intent = new Intent(getActivity(), PositionDetailsActivity.class);
        intent.putExtra("positionJson", new Gson().toJson(positionBean));
        startActivity(intent);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
