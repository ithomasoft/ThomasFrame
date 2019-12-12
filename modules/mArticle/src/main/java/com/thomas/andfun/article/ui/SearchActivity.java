package com.thomas.andfun.article.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.thomas.andfun.article.R;
import com.thomas.andfun.article.R2;
import com.thomas.andfun.article.adapter.HotKeyAdapter;
import com.thomas.andfun.article.bean.HotKeyBean;
import com.thomas.andfun.article.helper.FlowLayoutManager;
import com.thomas.andfun.article.ui.contract.SearchContract;
import com.thomas.andfun.article.ui.presenter.SearchPresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.service.RouterHub;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R2.id.rv_hot)
    RecyclerView rvHot;
    @BindView(R2.id.rv_history)
    RecyclerView rvHistory;
    private HotKeyAdapter hotKeyAdapter;

    private List<HotKeyBean> hotKeyDatas = new ArrayList<>();
    private List<String> historyDatas = new ArrayList<>();

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
            if (action == ThomasTitleBar.ACTION_SEARCH_SUBMIT) {
                Bundle bundle = new Bundle();
                bundle.putString("key", extra);
                ARouterHelper.startActivity(bundle, RouterHub.ROUTER_RESULT);
            }
            if (action == ThomasTitleBar.ACTION_RIGHT_TEXT) {
                Bundle bundle = new Bundle();
                bundle.putString("key", extra);
                ARouterHelper.startActivity(bundle, RouterHub.ROUTER_RESULT);
            }
        });

        hotKeyAdapter = new HotKeyAdapter(hotKeyDatas);
        rvHot.setLayoutManager(new FlowLayoutManager());
        rvHot.setAdapter(hotKeyAdapter);
        hotKeyAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("key", hotKeyDatas.get(position).getName());
            ARouterHelper.startActivity(bundle, RouterHub.ROUTER_RESULT);
        });
    }

    @Override
    public void doBusiness() {
        presenter.getHotKey();
    }

    @Override
    public void onFailed(String failed) {

    }

    @Override
    public void getHotKeySuccess(List<HotKeyBean> succeed) {
        hotKeyDatas.clear();
        hotKeyDatas.addAll(succeed);
        hotKeyAdapter.setNewData(hotKeyDatas);
    }

    @Override
    public void getHotKeyFailed(String failed) {
        ToastUtils.showShort(failed);
    }

}
