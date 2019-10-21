package com.thomas.andfun.home.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.thomas.core.utils.BarUtils;
import com.thomas.sdk.ui.ThomasMvpFragment;
import com.thomas.andfun.home.fragment.contract.ProjectContract;
import com.thomas.andfun.home.fragment.presenter.ProjectPresenter;
import com.thomas.andfun.home.R;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class ProjectFragment extends ThomasMvpFragment<ProjectPresenter> implements ProjectContract.View {


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
        BarUtils.setStatusBarLightMode(mActivity, true);
        return R.layout.fragment_project;
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
