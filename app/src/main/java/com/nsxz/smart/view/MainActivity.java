package com.nsxz.smart.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.nsxz.smart.R;
import com.nsxz.smart.model.UploadWorker;
import com.nsxz.smart.widget.FoldTextView;

import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.btn)
    Button btn;
    @Bind(R.id.tv)
    TextView tv;
    @Bind(R.id.ftv)
    FoldTextView ftv;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);


    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        Constraints constraints=new Constraints.Builder()
                .setRequiresCharging(true)
                .build();

        Data imageData=new Data.Builder()
                .putString("","")
                .build();


        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
//                .setConstraints(constraints)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setInputData(imageData)
                .build();
        WorkManager.getInstance().enqueue(uploadWorkRequest);
    }
}
