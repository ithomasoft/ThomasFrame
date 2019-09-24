package com.thomas.base.ui;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ClickUtils;
import com.blankj.utilcode.util.ScreenUtils;

/**
 * @author Thomas
 * @describe 基础Activity
 * @date 2019/9/24
 * @updatelog
 * @since 1.0.0
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    protected View mContentView;
    protected AppCompatActivity mActivity;

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onThomasClick(v);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        //默认实现沉浸式状态栏
        initStatusBar();
        initData(getIntent().getExtras());
        setRootLayout(bindLayout());
        initView(savedInstanceState, mContentView);
    }

    public void initStatusBar() {
        //默认强制竖屏
        ScreenUtils.setPortrait(this);
        if (BarUtils.isNavBarVisible(this)) {
            BarUtils.setNavBarVisibility(this, !isTransparent());
        }
        if (BarUtils.isSupportNavBar()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                BarUtils.setNavBarColor(this, ContextCompat.getColor(this, android.R.color.black));
            }
        }
    }

    @Override
    public void onAttachedToWindow() {
        doBusiness();
    }

    /**
     * 是否进行屏幕适配
     *
     * @return true：进行；false：不进行
     */
    protected abstract boolean isNeedAdapt();

    /**
     * 设置设计图的宽度（pt）
     *
     * @return
     */
    protected abstract int setAdaptScreen();

    @SuppressLint("ResourceType")
    @Override
    public void setRootLayout(@LayoutRes int layoutId) {
        if (layoutId <= 0) {
            return;
        }
        setContentView(mContentView = LayoutInflater.from(this).inflate(layoutId, null));
    }

    public void applyThomasClickListener(View... views) {
        ClickUtils.applyGlobalDebouncing(views, mClickListener);
        ClickUtils.applyScale(views);
    }

    @Override
    public Resources getResources() {
        if (isNeedAdapt()) {
            //今日头条屏幕适配方案

            if (ScreenUtils.isPortrait()) {
                //竖屏以宽度为基准
                return AdaptScreenUtils.adaptWidth(super.getResources(), setAdaptScreen());
            } else {
                //横屏以高度为基准
                return AdaptScreenUtils.adaptHeight(super.getResources(), setAdaptScreen());
            }

        } else {
            return super.getResources();
        }
    }
}
