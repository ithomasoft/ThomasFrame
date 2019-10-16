package com.thomas.sdk.arms;

import android.app.Application;

/**
 * @author Thomas
 * @describe 组件化的初始化接口
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public interface ConfigModule {
    void onCreate(Application application);
}
