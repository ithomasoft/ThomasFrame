package com.thomas.andfun.article.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.service.RouterHub;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.andfun.article.ui.contract.SearchContract;
import com.thomas.andfun.article.ui.presenter.SearchPresenter;
import com.thomas.andfun.article.R;
import com.thomas.andfun.article.R2;


import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/13
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_SEARCH)
public class SearchActivity extends ThomasMvpActivity<SearchPresenter> implements SearchContract.View {
    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;

    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
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
        return R.layout.activity_search;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
            if (action == ThomasTitleBar.ACTION_RIGHT_TEXT) {
                ToastUtils.showShort("搜索");
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
