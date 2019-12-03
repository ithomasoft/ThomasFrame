package com.thomas.andfun.user.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.thomas.andfun.user.R;
import com.thomas.andfun.user.R2;
import com.thomas.andfun.user.adapter.CollectionAdapter;
import com.thomas.andfun.user.bean.CollectionListBean;
import com.thomas.andfun.user.ui.contract.CollectionContract;
import com.thomas.andfun.user.ui.presenter.CollectionPresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.dialog.NormalDialog;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.service.RouterHub;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.DialogHelper;
import com.thomas.sdk.helper.LoadingHelper;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_COLLECTION)
public class CollectionActivity extends ThomasMvpActivity<CollectionPresenter> implements CollectionContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;


    private int page = 0;
    private List<CollectionListBean.DatasBean> datas = new ArrayList<>();
    private CollectionAdapter adapter;

    @Override
    protected CollectionPresenter createPresenter() {
        return new CollectionPresenter();
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
        return R.layout.activity_collection;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
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
                presenter.getCollectionList(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                datas.clear();
                presenter.getCollectionList(page);
            }
        });
        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new CollectionAdapter(datas);
        rvContent.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", datas.get(position).getLink());
            ARouterHelper.startActivity(bundle, RouterHub.ROUTER_ARTICLE);
        });
        adapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                showCancelDialog(position);
                return true;
            }
        });

    }

    private void showCancelDialog(int position) {
        DialogHelper.showDialogCenter("提示", "确定要取消收藏这篇文章吗?", "再想想", "确定", new NormalDialog.OnDialogListener() {
            @Override
            public void onCancel() {
            }

            @Override
            public void onSure() {
                presenter.unCollection(position, datas.get(position).getId(), datas.get(position).getOriginId());
            }
        });
    }

    @Override
    public void doBusiness() {
        LoadingHelper.showLoading();
        presenter.getCollectionList(page);
    }

    @Override
    public void onFailed(String failed) {
        smartRefreshLayout.finishRefresh(false);
        smartRefreshLayout.finishLoadMore(false);
        if (page == 0) {
            holder.withData(failed).withRetry(() -> presenter.getCollectionList(page)).showLoadFailed();
        } else {
            ToastUtils.showShort(failed);
        }
    }

    @Override
    public void onSuccess(List<CollectionListBean.DatasBean> datas) {
        smartRefreshLayout.finishRefresh(true);
        smartRefreshLayout.finishLoadMore(true);
        adapter.addData(datas);
    }

    @Override
    public void onEmpty() {
        holder.showEmpty();
    }

    @Override
    public void onMoreData(boolean hasMoreData) {
        smartRefreshLayout.setNoMoreData(!hasMoreData);
    }

    @Override
    public void onUnCollectionSuccess(int position) {
        adapter.remove(position);
        if (adapter.getData().size() == 0) {
            holder.showEmpty();
        } else {
            holder.showLoadSuccess();
        }
    }


    @Override
    public void onUnCollectionFailed(String failed) {
        ToastUtils.showShort(failed);
    }
}
