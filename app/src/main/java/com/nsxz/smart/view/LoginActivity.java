package com.nsxz.smart.view;

import android.os.Bundle;
import android.widget.Button;

import com.nsxz.smart.R;
import com.nsxz.smart.bean.User;
import com.nsxz.smart.model.LoginViewModel;

import androidx.lifecycle.Observer;
import butterknife.Bind;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.btnLogin)
    Button btnLogin;

    LoginViewModel viewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        viewModel = getViewModel(LoginViewModel.class);
        viewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {

            }
        });
    }


    @OnClick(R.id.btnLogin)
    public void onViewClicked() {
//        viewModel.getUser().setValue(new User());
    }
}
