package com.thomas.andfun.home.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.thomas.andfun.home.R;
import com.thomas.core.utils.BarUtils;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.ui.ThomasActivity;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_HOME)
public class HomeActivity extends ThomasActivity {
    BottomNavigationView navView;

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initStatusBar() {
        super.initStatusBar();
        BarUtils.setStatusBarColor(this, ContextCompat.getColor(mActivity, android.R.color.black), false);
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

        navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public void doBusiness() {

    }

}
