package com.thomas.andfun;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.Utils;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.ui.ThomasActivity;

@Route(path = RouterHub.ROUTER_SPLASH)
public class SplashActivity extends ThomasActivity {

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

    }

    @Override
    public void doBusiness() {
        Utils.runOnUiThreadDelayed(new Runnable() {
            @Override
            public void run() {
                ARouterHelper.startActivity(RouterHub.ROUTER_HOME);
                ActivityUtils.finishActivity(mActivity);
            }
        }, 3000);
    }
}
