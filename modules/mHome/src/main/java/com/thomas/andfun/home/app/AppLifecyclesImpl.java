package com.thomas.andfun.home.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.thomas.core.component.AppLifecycles;
import com.thomas.core.utils.LogUtils;

public class AppLifecyclesImpl implements AppLifecycles {
    @Override
    public void attachBaseContext(@NonNull Context base) {

    }

    @Override
    public void onCreate(@NonNull Application application) {
        LogUtils.e("主页模块初始化");
    }

    @Override
    public void onTerminate(@NonNull Application application) {

    }
}
