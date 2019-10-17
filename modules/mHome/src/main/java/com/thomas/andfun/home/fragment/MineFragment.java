package com.thomas.andfun.home.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.thomas.sdk.ui.ThomasMvpFragment;
import com.thomas.andfun.home.fragment.contract.MineContract;
import com.thomas.andfun.home.fragment.presenter.MinePresenter;
import com.thomas.andfun.home.R;


import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class MineFragment extends ThomasMvpFragment<MinePresenter> implements MineContract.View {


    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
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
        return R.layout.fragment_mine;
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
