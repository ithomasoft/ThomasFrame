package com.thomas.sdk.helper;

import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class ARouterHelper {
    /**
     * 没有传参的ARouter跳转
     *
     * @param routerPath
     */
    public static void startActivity(String routerPath) {
        ARouter.getInstance().build(routerPath).navigation();
    }

    /**
     * 带参数的ARouter跳转
     *
     * @param bundle
     * @param routerPath
     */
    public static void startActivity(Bundle bundle, String routerPath) {
        ARouter.getInstance().build(routerPath).with(bundle).navigation();
    }
}
