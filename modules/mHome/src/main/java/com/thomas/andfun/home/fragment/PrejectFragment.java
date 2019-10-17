package com.thomas.andfun.home.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.thomas.sdk.ui.ThomasMvpFragment;
import com.thomas.andfun.home.fragment.contract.PrejectContract;
import com.thomas.andfun.home.fragment.presenter.PrejectPresenter;
import com.thomas.andfun.home.R;


import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class PrejectFragment extends ThomasMvpFragment<PrejectPresenter> implements PrejectContract.View {


    @Override
    protected PrejectPresenter createPresenter() {
        return new PrejectPresenter();
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
        return R.layout.fragment_preject;
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
