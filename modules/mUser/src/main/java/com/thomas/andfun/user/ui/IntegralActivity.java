package com.thomas.andfun.user.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.thomas.andfun.user.R;
import com.thomas.andfun.user.R2;
import com.thomas.andfun.user.adapter.IntegralAdapter;
import com.thomas.andfun.user.bean.IntegralBean;
import com.thomas.andfun.user.bean.IntegralListBean;
import com.thomas.andfun.user.fragment.MineIntegralCenterFragment;
import com.thomas.andfun.user.ui.contract.IntegralContract;
import com.thomas.andfun.user.ui.presenter.IntegralPresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.helper.LoadingHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_INTEGRAL)
public class IntegralActivity extends ThomasMvpActivity<IntegralPresenter> implements IntegralContract.View {


    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    private int page = 1;
    private List<IntegralListBean.DatasBean> datas = new ArrayList<>();
    private IntegralAdapter adapter;
    private MineIntegralCenterFragment centerFragment = new MineIntegralCenterFragment(mActivity);

    @Override
    protected IntegralPresenter createPresenter() {
        return new IntegralPresenter();
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
        return R.layout.activity_integral;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getIntegralList(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                datas.clear();
                presenter.getIntegralList(page);
            }
        });
        adapter = new IntegralAdapter(datas);
        rvContent.setAdapter(adapter);
        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void doBusiness() {
        presenter.getMyIntegral();
        LoadingHelper.showLoading();
        presenter.getIntegralList(page);

    }

    @Override
    public void onFailed(String failed) {
        smartRefreshLayout.finishRefresh(false);
        smartRefreshLayout.finishLoadMore(false);
        if (page == 1) {
            holder.withData(failed).withRetry(() -> presenter.getIntegralList(page)).showLoadFailed();
        } else {
            ToastUtils.showShort(failed);
        }
    }

    @Override
    public void onSuccess(List<IntegralListBean.DatasBean> datas) {
        smartRefreshLayout.finishRefresh(true);
        smartRefreshLayout.finishLoadMore(true);
        adapter.addData(datas);
    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onMoreData(boolean hasMoreData) {
        smartRefreshLayout.setNoMoreData(!hasMoreData);
    }

    @Override
    public void onMyIntegralSuccess(IntegralBean data) {
        centerFragment.showPopupWindow();
        centerFragment.setData(data);
    }

    @Override
    public void onMyIntegralFailed(String failed) {

    }
}
