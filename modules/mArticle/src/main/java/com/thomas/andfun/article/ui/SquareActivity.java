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
import com.thomas.andfun.article.action.SharedAction;
import com.thomas.andfun.article.adapter.SquareAdapter;
import com.thomas.andfun.article.bean.SquareBean;
import com.thomas.andfun.article.ui.contract.SquareContract;
import com.thomas.andfun.article.ui.presenter.SquarePresenter;
import com.thomas.andfun.article.valid.LoginValid;
import com.thomas.core.delay.SingleCall;
import com.thomas.core.utils.ActivityUtils;
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
 * @date 2019/11/13
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_SQUARE)
public class SquareActivity extends ThomasMvpActivity<SquarePresenter> implements SquareContract.View {


    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    private int page = 0;
    private List<SquareBean.DatasBean> datas = new ArrayList<>();
    private SquareAdapter adapter;

    @Override
    protected SquarePresenter createPresenter() {
        return new SquarePresenter();
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
        return R.layout.activity_square;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
            if (action == ThomasTitleBar.ACTION_RIGHT_BUTTON) {
                SingleCall.getInstance().addAction(new SharedAction()).addValid(new LoginValid()).doCall();
            }
        });
        holder = StatusHelper.getDefault().wrap(smartRefreshLayout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getSquareArticle(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                datas.clear();
                presenter.getSquareArticle(page);
            }
        });

        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new SquareAdapter(datas);
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
        presenter.getSquareArticle(page);
    }

    @Override
    public void onFailed(Object tag, String failed) {
        smartRefreshLayout.finishRefresh(false);
        smartRefreshLayout.finishLoadMore(false);
        if (page == 0) {
            holder.withData(failed).withRetry(() -> presenter.getSquareArticle(page)).showLoadFailed();
        } else {
            ToastUtils.showShort(failed);
        }
    }

    @Override
    public void getSquareArticleSuccess(List<SquareBean.DatasBean> succeed) {
        smartRefreshLayout.finishRefresh(true);
        smartRefreshLayout.finishLoadMore(true);
        adapter.addData(succeed);
        holder.showLoadSuccess();
    }

    @Override
    public void getSquareArticleEmpty() {
        smartRefreshLayout.finishRefresh();
        holder.showEmpty();
    }

    @Override
    public void onMoreData(boolean hasMoreData) {
        smartRefreshLayout.setNoMoreData(!hasMoreData);
    }
}
