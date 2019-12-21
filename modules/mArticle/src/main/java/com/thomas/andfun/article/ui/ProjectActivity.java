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
import com.thomas.andfun.article.adapter.ProjectAdapter;
import com.thomas.andfun.article.bean.ProjectBean;
import com.thomas.andfun.article.ui.contract.ProjectContract;
import com.thomas.andfun.article.ui.presenter.ProjectPresenter;
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
@Route(path = RouterHub.ROUTER_PROJECT)
public class ProjectActivity extends ThomasMvpActivity<ProjectPresenter> implements ProjectContract.View {


    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    private int id;
    private String title;
    private int page = 1;
    private List<ProjectBean.DatasBean> datas = new ArrayList<>();
    private ProjectAdapter adapter;


    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter();
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
        return R.layout.activity_project;
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
                presenter.getCateProject(id, page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                datas.clear();
                presenter.getCateProject(id, page);
            }
        });

        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new ProjectAdapter(datas);
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
        presenter.getCateProject(id, page);
    }

    @Override
    public void onFailed(String failed) {
        smartRefreshLayout.finishRefresh(false);
        smartRefreshLayout.finishLoadMore(false);
        if (page == 1) {
            holder.withData(failed).withRetry(() -> presenter.getCateProject(id, page)).showLoadFailed();
        } else {
            ToastUtils.showShort(failed);
        }
    }

    @Override
    public void getCateProjectSuccess(List<ProjectBean.DatasBean> succeed) {
        smartRefreshLayout.finishRefresh(true);
        smartRefreshLayout.finishLoadMore(true);
        adapter.addData(succeed);
        holder.showLoadSuccess();
    }

    @Override
    public void getCateProjectEmpty() {
        holder.showEmpty();
    }

    @Override
    public void onMoreData(boolean hasMoreData) {
        smartRefreshLayout.setNoMoreData(!hasMoreData);
    }

}
