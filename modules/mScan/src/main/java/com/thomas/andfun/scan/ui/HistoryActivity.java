package com.thomas.andfun.scan.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.thomas.andfun.scan.adapter.ResultAdapter;
import com.thomas.andfun.scan.entity.ScanResult;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.core.utils.Utils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.andfun.scan.ui.contract.HistoryContract;
import com.thomas.andfun.scan.ui.presenter.HistoryPresenter;
import com.thomas.andfun.scan.R;
import com.thomas.andfun.scan.R2;
import com.thomas.service.RouterHub;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe 扫码记录页
 * @date 2019/12/31
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_SCAN_HISTORY)
public class HistoryActivity extends ThomasMvpActivity<HistoryPresenter> implements HistoryContract.View {
    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private List<ScanResult> datas = new ArrayList<>();
    private ResultAdapter adapter;

    @Override
    protected HistoryPresenter createPresenter() {
        return new HistoryPresenter();
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
        return R.layout.activity_history;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
        if (holder == null) {
            holder = StatusHelper.getDefault().wrap(smartRefreshLayout).withRetry(new Runnable() {
                @Override
                public void run() {
                    holder.showLoading();
                    presenter.getData();
                }
            });
        }
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                datas.clear();
                presenter.getData();
            }
        });
        adapter = new ResultAdapter(datas);
        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        rvContent.setAdapter(adapter);
    }

    @Override
    public void doBusiness() {
        holder.showLoading();
        Utils.runOnUiThreadDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getData();
            }
        }, 1000);

    }

    @Override
    public void onFailed(String failed) {
        smartRefreshLayout.finishRefresh(false);
        holder.withData(failed).showLoadFailed();
    }

    @Override
    public void getDataSuccess(List<ScanResult> succeed) {
        smartRefreshLayout.finishRefresh(true);
        holder.showLoadSuccess();
        datas.clear();
        datas.addAll(succeed);
        adapter.setNewData(datas);
    }

    @Override
    public void getDataEmpty() {
        smartRefreshLayout.finishRefresh(true);
        holder.showEmpty();
    }
}
