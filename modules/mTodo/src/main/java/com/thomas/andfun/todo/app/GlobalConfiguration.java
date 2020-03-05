package com.thomas.andfun.todo.app;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.thomas.core.component.AppLifecycles;
import com.thomas.core.component.ModuleConfig;

import java.util.List;

/**
 * ================================================
 *
 * <p>
 *
 * @author Thomas
 * @describe 组件化中需要初始化的操作
 * @date 2019/10/17
 * @updatelog
 * @since ================================================
 */
public class GlobalConfiguration implements ModuleConfig {

    @Override
    public void injectAppLifecycle(@NonNull Context context, @NonNull List<AppLifecycles> lifecycles) {

    }

    @Override
    public void injectActivityLifecycle(@NonNull Context context, @NonNull List<Application.ActivityLifecycleCallbacks> lifecycles) {

    }

    @Override
    public void injectFragmentLifecycle(@NonNull Context context, @NonNull List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }
}