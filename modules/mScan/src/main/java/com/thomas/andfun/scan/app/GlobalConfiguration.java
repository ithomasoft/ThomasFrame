package com.thomas.andfun.scan.app;

import android.app.Application;
import android.content.Context;


import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.thomas.core.component.AppLifecycles;
import com.thomas.core.component.ModuleConfig;

import org.litepal.LitePal;

import java.util.List;

/**
 * ================================================
 *
 * <p>
 *
 * @author Thomas
 * @describe 组件化中需要初始化的操作
 * @date 2019/12/12
 * @updatelog
 * @since ================================================
 */
public class GlobalConfiguration implements ModuleConfig {

    @Override
    public void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> lifecycles) {
        lifecycles.add(new AppLifecyclesImpl());
    }

    @Override
    public void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> lifecycles) {

    }

    @Override
    public void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }
}