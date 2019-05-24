package com.nsxz.smart.model;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UploadWorker extends Worker {


    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data=getInputData();
        String uri=data.getString("");
        Log.i(getClass().getCanonicalName(),"sdfsd");
        return Result.success(data);
    }

    @Override
    public void onStopped() {
        super.onStopped();
    }
}
