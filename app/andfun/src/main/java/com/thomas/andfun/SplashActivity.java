package com.thomas.andfun;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.Utils;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.ui.ThomasActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;

@Route(path = RouterHub.ROUTER_SPLASH)
public class SplashActivity extends ThomasActivity {

    @BindView(R.id.lottie_splash)
    LottieAnimationView lottieSplash;

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
        lottieSplash.addLottieOnCompositionLoadedListener(composition -> Utils.runOnUiThreadDelayed(() -> {
            ARouterHelper.startActivity(RouterHub.ROUTER_HOME);
            ActivityUtils.finishActivity(mActivity);
        }, lottieSplash.getDuration() * 2));


    }
}
