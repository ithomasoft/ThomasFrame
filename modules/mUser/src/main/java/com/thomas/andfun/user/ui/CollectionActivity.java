package com.thomas.andfun.user.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.andfun.user.ui.contract.CollectionContract;
import com.thomas.andfun.user.ui.presenter.CollectionPresenter;
import com.thomas.andfun.user.R;
import com.thomas.andfun.user.R2;


import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_COLLECTION)
public class CollectionActivity extends ThomasMvpActivity<CollectionPresenter> implements CollectionContract.View {


    @Override
    protected CollectionPresenter createPresenter() {
        return new CollectionPresenter();
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
        return R.layout.activity_collection;
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
