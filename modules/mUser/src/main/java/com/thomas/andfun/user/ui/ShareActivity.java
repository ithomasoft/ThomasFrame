package com.thomas.andfun.user.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.andfun.user.ui.contract.ShareContract;
import com.thomas.andfun.user.ui.presenter.SharePresenter;
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
@Route(path = RouterHub.ROUTER_SHARE)
public class ShareActivity extends ThomasMvpActivity<SharePresenter> implements ShareContract.View {


    @Override
    protected SharePresenter createPresenter() {
        return new SharePresenter();
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
        return R.layout.activity_share;
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
