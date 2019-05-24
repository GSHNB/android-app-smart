package com.nsxz.smart.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nsxz.smart.R;
import com.nsxz.smart.model.LoginViewModel;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvUserName)
    TextView tvUserName;
    @BindView(R.id.editUserName)
    EditText editUserName;
    @BindView(R.id.tvPassword)
    TextView tvPassword;
    @BindView(R.id.editPassword)
    EditText editPassword;

    LoginViewModel viewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        viewModel = getViewModel(LoginViewModel.class);
        viewModel.getUser().observe(this, data->{});
    }




    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
    }
}
