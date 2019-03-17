package com.admn.recruit.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.admn.recruit.R;
import com.admn.recruit.basicInfo.BasicInfoActivity;
import com.admn.recruit.eduexp.EduExpActivity;
import com.admn.recruit.target.TargetActivity;
import com.admn.recruit.workexp.WorkExpActivity;


public class ResumeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private View view;
    private TextView btn_basic_info;
    private TextView btn_work_exp;
    private TextView btn_edu_exp;
    private TextView btn_target;


    public ResumeFragment() {}

    public static ResumeFragment newInstance(String param1, String param2) {
        ResumeFragment fragment = new ResumeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_resume, container, false);
        initView();
        return view;
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 点击事件
        btn_basic_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(BasicInfoActivity.class);
            }
        });

        btn_work_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(WorkExpActivity.class);
            }
        });

        btn_edu_exp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(EduExpActivity.class);
            }
        });

        btn_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jumpActivity(TargetActivity.class);
            }
        });

    }


    private void initView() {
        btn_basic_info = view.findViewById(R.id.btn_basic_info);
        btn_work_exp = view.findViewById(R.id.btn_work_exp);
        btn_edu_exp = view.findViewById(R.id.btn_edu_exp);
        btn_target = view.findViewById(R.id.btn_target);

    }


    public void jumpActivity(Class activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }






}





