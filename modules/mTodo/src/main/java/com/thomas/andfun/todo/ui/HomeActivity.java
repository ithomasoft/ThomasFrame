package com.thomas.andfun.todo.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.andfun.todo.ui.contract.HomeContract;
import com.thomas.andfun.todo.ui.presenter.HomePresenter;
import com.thomas.andfun.todo.R;
import com.thomas.andfun.todo.R2;


import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
@Route(path = "")//TODO 添加路由path
public class HomeActivity extends ThomasMvpActivity<HomePresenter> implements HomeContract.View {


    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
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
        return R.layout.activity_home;
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
