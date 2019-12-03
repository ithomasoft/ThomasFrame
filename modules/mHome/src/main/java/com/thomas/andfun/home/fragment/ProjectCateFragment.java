package com.thomas.andfun.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thomas.andfun.home.R;
import com.thomas.andfun.home.R2;
import com.thomas.andfun.home.adapter.ProjectCateAdapter;
import com.thomas.andfun.home.bean.ProjectCateBean;
import com.thomas.andfun.home.fragment.contract.ProjectCateContract;
import com.thomas.andfun.home.fragment.presenter.ProjectCatePresenter;
import com.thomas.core.utils.BarUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.LazyThomasMvpFragment;
import com.thomas.service.RouterHub;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class ProjectCateFragment extends LazyThomasMvpFragment<ProjectCatePresenter> implements ProjectCateContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    private ProjectCateAdapter adapter;
    private List<ProjectCateBean> datas = new ArrayList<>();

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

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_project_cate;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        holder = StatusHelper.getDefault().wrap(rvContent);
        adapter = new ProjectCateAdapter(datas);
        rvContent.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 2);
        rvContent.setLayoutManager(gridLayoutManager);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putInt("id", datas.get(position).getId());
            bundle.putString("title", datas.get(position).getName());
            ARouterHelper.startActivity(bundle, RouterHub.ROUTER_PROJECT);
        });

    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_title_background));
        BarUtils.addMarginTopEqualStatusBarHeight(titleBar);
        holder.showLoading();
        presenter.getProject();
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_title_background));
        BarUtils.addMarginTopEqualStatusBarHeight(titleBar);
    }

    @Override
    public void onFailed(String failed) {
        holder.withData(failed).withRetry(() -> presenter.getProject()).showLoadFailed();
    }

    @Override
    public void getCateSuccess(List<ProjectCateBean> datas) {
        holder.showLoadSuccess();
        this.datas = datas;
        adapter.setNewData(datas);

    }

    @Override
    public void getCateEmpty() {
        holder.showEmpty();
    }
}
