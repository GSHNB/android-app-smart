package com.nsxz.smart.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.multidex.MultiDex;

import com.meituan.android.walle.WalleChannelReader;
import com.nsxz.smart.BuildConfig;
import com.nsxz.smart.network.NetWorkManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.tinker.entry.DefaultApplicationLike;

/**
 * Created by gaoshun on 2019/2/21.
 */

public class SmartApplicationLike extends DefaultApplicationLike {
    public SmartApplicationLike(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime, long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Bugly.setIsDevelopmentDevice(getApplication(), BuildConfig.DEBUG);
        String channel = WalleChannelReader.getChannel(getApplication());
        Bugly.setAppChannel(getApplication(), channel);
        Bugly.init(getApplication(), Const.BUGLY_APPID, BuildConfig.DEBUG);
        NetWorkManager.getInstance().init();
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

}
