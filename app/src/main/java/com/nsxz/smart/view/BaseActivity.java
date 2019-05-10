package com.nsxz.smart.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.nsxz.smart.R;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private FrameLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        init(savedInstanceState);
    }

    public abstract @LayoutRes
    int getLayoutId();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(R.layout.activity_base);
        baseLayout = findViewById(R.id.base_content);
        LayoutInflater.from(this).inflate(layoutResID, baseLayout, true);
        ButterKnife.bind(this);
    }

    public FrameLayout getBaseLayout() {
        return baseLayout;
    }

    public void init(Bundle savedInstanceState) {

    }

    public <T extends ViewModel> T getViewModel(@NonNull Class<T> modelClass) {
        return ViewModelProviders.of(this).get(modelClass);
    }
}
