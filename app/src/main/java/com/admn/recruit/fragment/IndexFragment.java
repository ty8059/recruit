package com.admn.recruit.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.activity.AboutUsActivity;
import com.admn.recruit.activity.PositionAppActivity;
import com.admn.recruit.activity.InviteActivity;
import com.admn.recruit.activity.PositionDetailsActivity;
import com.admn.recruit.activity.RemindActivity;
import com.admn.recruit.adapter.PositionAdapter;
import com.admn.recruit.model.Position;
import com.admn.recruit.presenter.PositionPresenter;
import com.admn.recruit.view.IndexView;
import com.admn.recruit.view.PositionView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment implements IndexView, View.OnClickListener, ListView.OnItemClickListener, PositionView {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 1;

    private String mParam1;
    private String mParam2;

    private ImageButton btn_application_record;
    private ImageButton btn_invitation;
    private ImageButton btn_remind;
    private ImageButton btn_about_us;
    private ListView lv_newest_position;
    private View view;

    private List<Position> positionList;
    private PositionPresenter positionPresenter;

    public IndexFragment() {}

    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index, container, false);
        initView();
        positionPresenter = new PositionPresenter(this);
        positionPresenter.getPositionList();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn_application_record.setOnClickListener(this);
        btn_invitation.setOnClickListener(this);
        btn_remind.setOnClickListener(this);
        btn_about_us.setOnClickListener(this);
    }


    private void initView() {
        btn_application_record = view.findViewById(R.id.btn_application_record);
        btn_invitation = view.findViewById(R.id.btn_invitation);
        btn_remind = view.findViewById(R.id.btn_remind);
        btn_about_us = view.findViewById(R.id.btn_about_us);
        lv_newest_position = view.findViewById(R.id.lv_newest_position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    /**
     * 检查权限
     */
    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(getActivity(), "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
                Log.e("Upload_Permission", "请开通相关权限，否则无法正常使用本应用！");
            }
            //申请权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);
        } else {
            //Toast.makeText(getActivity(), "授权成功！", Toast.LENGTH_SHORT).show();
            Log.d("Upload_Permission", "checkPermission: 已经授权！");
        }
    }

    @Override
    public void jumpToApplicationRecordActivity() {
        Intent intent = new Intent(getActivity(), PositionAppActivity.class);
        startActivity(intent);
    }

    @Override
    public void jumpToInvitationActivity() {
        Intent intent = new Intent(getActivity(), InviteActivity.class);
        startActivity(intent);
    }

    @Override
    public void jumpToRemindActivity() {
        Intent intent = new Intent(getActivity(), RemindActivity.class);
        startActivity(intent);
    }

    @Override
    public void jumpToAboutUsActivity() {
        Intent intent = new Intent(getActivity(), AboutUsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_application_record:
                jumpToApplicationRecordActivity();
                break;
            case R.id.btn_invitation:
                jumpToInvitationActivity();
                break;
            case R.id.btn_remind:
                jumpToRemindActivity();
                break;
            case R.id.btn_about_us:
                jumpToAboutUsActivity();
                break;
            default:break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Position positionBean = positionList.get(i);
        Intent intent = new Intent(getActivity(), PositionDetailsActivity.class);
        intent.putExtra("positionJson", new Gson().toJson(positionBean));
        startActivity(intent);
    }

    @Override
    public void initPositionData(List<Position> positionList) {
        // 获取职位表
        this.positionList = positionList;
        List<Position> list = new ArrayList<>();
        for (int i = 0; i < positionList.size(); i++) {
            if (positionList.get(i) != null) {
                list.add(positionList.get(i));
            }
            if (list.size() == 3 || positionList.get(i) == null) {
                break;
            }
        }
        PositionAdapter positionAdapter = new PositionAdapter(getActivity(), R.layout.position_item, list);
        lv_newest_position.setAdapter(positionAdapter);
        lv_newest_position.setOnItemClickListener(this);
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
