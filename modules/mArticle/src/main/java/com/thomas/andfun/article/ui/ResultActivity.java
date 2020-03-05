package com.thomas.andfun.article.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.thomas.andfun.article.R;
import com.thomas.andfun.article.R2;
import com.thomas.andfun.article.adapter.ResultAdapter;
import com.thomas.andfun.article.bean.ResultBean;
import com.thomas.andfun.article.ui.contract.ResultContract;
import com.thomas.andfun.article.ui.presenter.ResultPresenter;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.service.RouterHub;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_RESULT)
public class ResultActivity extends ThomasMvpActivity<ResultPresenter> implements ResultContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    private String key;
    private int pageNo = 0;

    private ResultAdapter adapter;
    private List<ResultBean.DatasBean> datas = new ArrayList<>();

    @Override
    protected ResultPresenter createPresenter() {
        return new ResultPresenter();
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        key = bundle.getString("key");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_result;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.getCenterTextView().setText(key + " 的搜索结果");
        holder = StatusHelper.getDefault().wrap(smartRefreshLayout).withRetry(new Runnable() {
            @Override
            public void run() {
                pageNo = 0;
                presenter.getData(pageNo, key);
            }
        });
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNo++;
                presenter.getData(pageNo, key);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNo = 0;
                datas.clear();
                presenter.getData(pageNo, key);
            }
        });

        adapter = new ResultAdapter(datas);
        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        rvContent.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", datas.get(position).getLink());
            ARouterHelper.startActivity(bundle, RouterHub.ROUTER_ARTICLE);
        });
    }

    @Override
    public void doBusiness() {
        holder.showLoading();
        presenter.getData(pageNo, key);
    }

    @Override
    public void onFailed(Object tag, String failed) {
        smartRefreshLayout.finishRefresh(false);
        smartRefreshLayout.finishLoadMore(false);
        if (pageNo == 0) {
            holder.withData(failed).showLoadFailed();
        } else {
            ToastUtils.showShort(failed);
        }
    }

    @Override
    public void getDataSuccess(List<ResultBean.DatasBean> succeed) {
        smartRefreshLayout.finishRefresh(true);
        smartRefreshLayout.finishLoadMore(true);
        holder.showLoadSuccess();
        adapter.addData(succeed);
    }

    @Override
    public void getDataEmpty() {
        holder.showEmpty();
        smartRefreshLayout.finishRefresh(true);
        smartRefreshLayout.finishLoadMore(true);
    }

    @Override
    public void onMoreData(boolean hasMoreData) {
        smartRefreshLayout.setNoMoreData(!hasMoreData);
    }
}
