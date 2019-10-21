package com.thomas.andfun.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.thomas.andfun.home.R;
import com.thomas.andfun.home.fragment.contract.MineContract;
import com.thomas.andfun.home.fragment.presenter.MinePresenter;
import com.thomas.core.utils.BarUtils;
import com.thomas.core.utils.LogUtils;
import com.thomas.sdk.ui.LazyThomasMvpFragment;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class MineFragment extends LazyThomasMvpFragment<MinePresenter> implements MineContract.View {


    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        LogUtils.e("initData");
    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        LogUtils.e("onFirstUserVisible");
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
        LogUtils.e("onUserVisible");
    }

    @Override
    protected void onUserInvisible() {
        super.onUserInvisible();
        LogUtils.e("onUserInvisible");
    }

    @Override
    protected void destroyViewAndThing() {
        super.destroyViewAndThing();
        LogUtils.e("destroyViewAndThing");
    }

    @Override
    public int bindLayout() {
        BarUtils.setStatusBarLightMode(mActivity, false);
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        LogUtils.e("initView");
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onThomasClick(@NonNull View view) {

    }

    @Override
    public void onFailed(String failed) {

    }
}
