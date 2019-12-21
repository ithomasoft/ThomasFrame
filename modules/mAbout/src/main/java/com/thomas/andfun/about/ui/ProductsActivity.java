package com.thomas.andfun.about.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.andfun.about.R;
import com.thomas.andfun.about.R2;
import com.thomas.andfun.about.ui.contract.ProductsContract;
import com.thomas.andfun.about.ui.presenter.ProductsPresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/13
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_APP)
public class ProductsActivity extends ThomasMvpActivity<ProductsPresenter> implements ProductsContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;

    @Override
    protected ProductsPresenter createPresenter() {
        return new ProductsPresenter();
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
        return R.layout.activity_products;
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
