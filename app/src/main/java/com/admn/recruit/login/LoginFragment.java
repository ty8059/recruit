package com.admn.recruit.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.admn.recruit.R;
import com.admn.recruit.index.IndexActivity;
import com.admn.recruit.register.RegisterActivity;


public class LoginFragment extends Fragment implements ILoginView{

    private View view;
    private ProgressBar progressBar;
    private EditText usernameET;
    private EditText passwordET;
    private Button loginBtn;
    private Button registerBtn;

    private LoginPresenter loginPresenter;

    public static LoginFragment getInstance(){
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        initView();
        loginPresenter = new LoginPresenter(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 登录按钮的点击事件
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行登录操作
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                loginPresenter.doLogin(username, password);
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行注册操作
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        progressBar = view.findViewById(R.id.login_pb);
        usernameET = view.findViewById(R.id.username_et);
        passwordET = view.findViewById(R.id.password_et);
        loginBtn = view.findViewById(R.id.login_btn);
        registerBtn = view.findViewById(R.id.btn_register);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showMsg(String msg) {
        Toast.makeText(this.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hiddenLoading() {

    }

    @Override
    public void jumpActivity() {
        Intent intent = new Intent(getActivity(), IndexActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean back() {
        return false;
    }
}
