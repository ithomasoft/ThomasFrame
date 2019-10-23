package com.thomas.andfun.login.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.andfun.login.ui.contract.RegisterContract;
import com.thomas.andfun.login.ui.presenter.RegisterPresenter;
import com.thomas.andfun.login.R;
import com.thomas.andfun.login.R2;


import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_REGISTER)
public class RegisterActivity extends ThomasMvpActivity<RegisterPresenter> implements RegisterContract.View {


    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
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
        return R.layout.activity_register;
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
