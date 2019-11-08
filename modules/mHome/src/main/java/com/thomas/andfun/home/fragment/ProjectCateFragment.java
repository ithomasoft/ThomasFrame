package com.thomas.andfun.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.R2;
import com.thomas.andfun.home.adapter.ProjectAdapter;
import com.thomas.andfun.home.bean.ProjectBean;
import com.thomas.andfun.home.fragment.contract.ProjectCateContract;
import com.thomas.andfun.home.fragment.presenter.ProjectCatePresenter;
import com.thomas.core.utils.ToastUtils;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.LazyThomasMvpFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/08
 * @updatelog
 * @since
 */
public class ProjectCateFragment extends LazyThomasMvpFragment<ProjectCatePresenter> implements ProjectCateContract.View {

    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    private int id;
    private int page = 1;
    private List<ProjectBean.DatasBean> datas = new ArrayList<>();
    private ProjectAdapter adapter;

    public static ProjectCateFragment newInstance(int id) {
        ProjectCateFragment fragment = new ProjectCateFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    private ProjectCateFragment() {
    }

    @Override
    protected ProjectCatePresenter createPresenter() {
        return new ProjectCatePresenter();
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        id = bundle.getInt("id");
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_project_cate;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
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
            ToastUtils.showShort(datas.get(position).getLink());
        });
    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        holder.showLoading();
        presenter.getCateProject(id, page);
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
        holder.showLoading();
        presenter.getCateProject(id, page);
    }

    @Override
    public void onThomasClick(@NonNull View view) {

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
