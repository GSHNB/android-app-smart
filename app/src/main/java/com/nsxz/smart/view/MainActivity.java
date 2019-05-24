package com.nsxz.smart.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.nsxz.smart.R;
import com.nsxz.smart.model.UploadWorker;
import com.nsxz.smart.widget.FoldTextView;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.ftv)
    FoldTextView ftv;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        startActivity(new Intent(this, LoginActivity.class));

    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        Constraints constraints = new Constraints.Builder()
//                .setRequiresCharging(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        Data imageData = new Data.Builder()
                .putString("", "")
                .build();


        OneTimeWorkRequest uploadWorkRequest = new OneTimeWorkRequest.Builder(UploadWorker.class)
                .setConstraints(constraints)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .setInputData(imageData)
                .build();
        WorkManager.getInstance().enqueue(uploadWorkRequest);
        WorkManager.getInstance().cancelWorkById(uploadWorkRequest.getId());

        PeriodicWorkRequest request = new PeriodicWorkRequest
                .Builder(UploadWorker.class, 12, TimeUnit.HOURS)
                .build();
        WorkManager.getInstance().beginWith(uploadWorkRequest)
                .then(uploadWorkRequest).enqueue();
        WorkContinuation c1 = WorkManager.getInstance().beginWith(Arrays.asList(uploadWorkRequest)).then(uploadWorkRequest);
        WorkContinuation c2 = WorkManager.getInstance().beginWith(uploadWorkRequest).then(uploadWorkRequest);
        WorkContinuation.combine(Arrays.asList(c1, c2)).then(uploadWorkRequest).enqueue();

        WorkManager.getInstance().getWorkInfoByIdLiveData(uploadWorkRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
            }
        });
        WorkManager.getInstance().enqueueUniqueWork("", ExistingWorkPolicy.APPEND, uploadWorkRequest);

    }
}
