package com.admn.recruit.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.adapter.PositionAdapter;
import com.admn.recruit.model.Position;

import java.util.ArrayList;
import java.util.List;

public class PositionFragment extends Fragment implements ListView.OnItemClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private ListView positionListView;
    private View view;
    private List<Position> positionList;

    private String mParam1;
    private String mParam2;

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
        positionList = initPositionData();
        PositionAdapter positionAdapter = new PositionAdapter(getActivity(), R.layout.position_item, positionList);
        positionListView.setAdapter(positionAdapter);
        positionListView.setOnItemClickListener(this);
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

    private List<Position> initPositionData() {
        List<Position> list = new ArrayList<>();
        return list;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Position positionBean = positionList.get(position);
        Toast.makeText(getActivity(), positionBean.getPositionName(), Toast.LENGTH_SHORT).show();
    }
}
