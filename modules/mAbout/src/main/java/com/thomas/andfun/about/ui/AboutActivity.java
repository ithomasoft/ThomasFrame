package com.thomas.andfun.about.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.thomas.andfun.about.R;
import com.thomas.andfun.about.R2;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.ui.ThomasActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/25
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_ABOUT)
public class AboutActivity extends ThomasActivity {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.btn_point)
    SuperTextView btnPoint;
    @BindView(R2.id.btn_web)
    SuperTextView btnWeb;
    @BindView(R2.id.btn_author)
    SuperTextView btnAuthor;
    @BindView(R2.id.btn_open)
    SuperTextView btnOpen;
    @BindView(R2.id.btn_app)
    SuperTextView btnApp;

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_about;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
        applyThomasClickListener(btnPoint, btnWeb, btnAuthor, btnOpen, btnApp);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onThomasClick(@NonNull View view) {
        int clickId = view.getId();
        if (clickId == R.id.btn_point) {
            ARouterHelper.startActivity(RouterHub.ROUTER_RULES);
        }
        if (clickId == R.id.btn_web) {
            ARouterHelper.startActivity(RouterHub.ROUTER_WEB);
        }
    }
}
