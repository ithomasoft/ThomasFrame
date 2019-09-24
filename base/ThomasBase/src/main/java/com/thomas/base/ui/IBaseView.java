package com.thomas.base.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author Thomas
 * @describe 约束视图中的一些操作方法
 * @date 2019/9/24
 * @updatelog
 * @since 1.0.0
 */
public interface IBaseView {
    /**
     * 是否需要注册EventBus
     *
     * @return
     */
    boolean isNeedRegister();

    /**
     * 是否是全屏透明，包含顶部状态栏和底部导航栏
     *
     * @return
     */
    boolean isTransparent();

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    void initData(@Nullable Bundle bundle);

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    int bindLayout();

    void setRootLayout(@LayoutRes int layoutId);

    /**
     * 初始化 view
     */
    void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView);

    /**
     * 业务操作
     */
    void doBusiness();

    /**
     * 框架中视图点击事件
     *
     * @param view 视图
     */
    void onThomasClick(@NonNull View view);
}
