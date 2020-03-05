package com.thomas.andfun.scan.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.thomas.core.component.AppLifecycles;

import org.litepal.LitePal;

public class AppLifecyclesImpl implements AppLifecycles {
    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        //初始化数据库框架，目前只有扫码模块用到了数据库
        LitePal.initialize(application);
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
