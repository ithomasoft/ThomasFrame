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
import com.thomas.andfun.article.adapter.KnowledgeAdapter;
import com.thomas.andfun.article.bean.KnowledgeBean;
import com.thomas.andfun.article.ui.contract.KnowledgeContract;
import com.thomas.andfun.article.ui.presenter.KnowledgePresenter;
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
 * @date 2019/11/12
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_KNOWLEDGE)
public class KnowledgeActivity extends ThomasMvpActivity<KnowledgePresenter> implements KnowledgeContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    private int id;
    private String title;
    private int page = 0;
    private List<KnowledgeBean.DatasBean> datas = new ArrayList<>();
    private KnowledgeAdapter adapter;

    @Override
    protected KnowledgePresenter createPresenter() {
        return new KnowledgePresenter();
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        id = bundle.getInt("id");
        title = bundle.getString("title");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_knowledge;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.getCenterTextView().setText(title);
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
        holder = StatusHelper.getDefault().wrap(smartRefreshLayout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getKnowledge(id, page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                datas.clear();
                presenter.getKnowledge(id, page);
            }
        });

        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new KnowledgeAdapter(datas);
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
        presenter.getKnowledge(id, page);
    }

    @Override
    public void onFailed(Object tag, String failed) {
        smartRefreshLayout.finishRefresh(false);
        smartRefreshLayout.finishLoadMore(false);
        if (page == 1) {
            holder.withData(failed).withRetry(() -> presenter.getKnowledge(id, page)).showLoadFailed();
        } else {
            ToastUtils.showShort(failed);
        }
    }

    @Override
    public void getKnowledgeSuccess(List<KnowledgeBean.DatasBean> succeed) {
        smartRefreshLayout.finishRefresh(true);
        smartRefreshLayout.finishLoadMore(true);
        adapter.addData(succeed);
        holder.showLoadSuccess();
    }

    @Override
    public void getKnowledgeEmpty() {
        holder.showEmpty();
    }

    @Override
    public void onMoreData(boolean hasMoreData) {
        smartRefreshLayout.setNoMoreData(!hasMoreData);
    }
}
