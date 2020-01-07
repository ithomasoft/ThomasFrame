package com.thomas.andfun.scan.app;

import android.app.Application;

import com.thomas.sdk.arms.ConfigModule;

import org.litepal.LitePal;

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
public class GlobalConfiguration implements ConfigModule {
    @Override
    public void onCreate(Application application) {
        //初始化数据库框架，目前只有扫码模块用到了数据库
        LitePal.initialize(application);
    }
}