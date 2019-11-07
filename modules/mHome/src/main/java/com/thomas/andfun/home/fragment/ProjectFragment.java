package com.thomas.andfun.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.R2;
import com.thomas.andfun.home.fragment.contract.ProjectContract;
import com.thomas.andfun.home.fragment.presenter.ProjectPresenter;
import com.thomas.core.utils.BarUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.ui.ThomasMvpFragment;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class ProjectFragment extends ThomasMvpFragment<ProjectPresenter> implements ProjectContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R2.id.vp_content)
    ViewPager vpContent;

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_project;
    }

    @Override
    public void onStart() {
        super.onStart();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_title_background));
        BarUtils.addMarginTopEqualStatusBarHeight(titleBar);
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onFailed(String failed) {

    }
}
