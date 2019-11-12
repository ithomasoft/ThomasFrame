package com.thomas.andfun.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.R2;
import com.thomas.andfun.home.adapter.KnowledgeAdapter;
import com.thomas.andfun.home.bean.KnowledgeBean;
import com.thomas.andfun.home.fragment.contract.KnowledgeContract;
import com.thomas.andfun.home.fragment.presenter.KnowledgePresenter;
import com.thomas.core.utils.BarUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.LazyThomasMvpFragment;

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
public class KnowledgeFragment extends LazyThomasMvpFragment<KnowledgePresenter> implements KnowledgeContract.View {


    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    private List<MultiItemEntity> datas = new ArrayList<>();
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

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        holder = StatusHelper.getDefault().wrap(rvContent);
        adapter = new KnowledgeAdapter(datas);
        rvContent.setAdapter(adapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mActivity, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return adapter.getItemViewType(position) == KnowledgeAdapter.TYPE_LEVEL_2 ? 1 : gridLayoutManager.getSpanCount();
            }
        });
        rvContent.setLayoutManager(gridLayoutManager);
        adapter.setOnItemChildClickListener((adapter, view, position) -> {
            if (datas.get(position).getItemType() == KnowledgeAdapter.TYPE_LEVEL_2) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", ((KnowledgeBean.ChildrenBean) datas.get(position)).getId());
                bundle.putString("title", ((KnowledgeBean.ChildrenBean) datas.get(position)).getName());
                ARouterHelper.startActivity(bundle, RouterHub.ROUTER_KNOWLEDGE);
            }

        });
    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_title_background));
        BarUtils.addMarginTopEqualStatusBarHeight(titleBar);
        holder.showLoading();
        presenter.getKnowledge();
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
        holder.withData(failed).withRetry(() -> presenter.getKnowledge()).showLoadFailed();
    }


    @Override
    public void getKnowledgeSuccess(List<MultiItemEntity> datas) {
        adapter.addData(datas);
        adapter.expandAll();
        holder.showLoadSuccess();
    }

    @Override
    public void onEmpty() {
        holder.showEmpty();
    }
}
