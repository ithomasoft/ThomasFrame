package com.thomas.andfun.about.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.andfun.about.ui.contract.OpenSourceContract;
import com.thomas.andfun.about.ui.presenter.OpenSourcePresenter;
import com.thomas.andfun.about.R;
import com.thomas.andfun.about.R2;
import com.thomas.service.RouterHub;


import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/13
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_OPEN)
public class OpenSourceActivity extends ThomasMvpActivity<OpenSourcePresenter> implements OpenSourceContract.View {
    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;

    @Override
    protected OpenSourcePresenter createPresenter() {
        return new OpenSourcePresenter();
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
        return R.layout.activity_open_source;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onFailed(String failed) {

    }

}
