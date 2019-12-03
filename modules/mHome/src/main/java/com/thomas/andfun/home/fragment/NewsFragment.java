package com.thomas.andfun.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.R2;
import com.thomas.andfun.home.adapter.NewsAdapter;
import com.thomas.andfun.home.bean.BannerBean;
import com.thomas.andfun.home.bean.NewsListBean;
import com.thomas.andfun.home.fragment.contract.NewsContract;
import com.thomas.andfun.home.fragment.presenter.NewsPresenter;
import com.thomas.core.utils.BarUtils;
import com.thomas.core.utils.ScreenUtils;
import com.thomas.core.utils.SizeUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.ImageHelper;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.LazyThomasMvpFragment;
import com.thomas.service.RouterHub;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

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
public class NewsFragment extends LazyThomasMvpFragment<NewsPresenter> implements NewsContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.rv_content)
    RecyclerView rvContent;
    @BindView(R2.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;
    Banner banner;

    private List<String> images = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private List<NewsListBean.DatasBean> datas = new ArrayList<>();
    private int page = 0;
    private NewsAdapter adapter;

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
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
        return R.layout.fragment_news;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        initHead();
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_RIGHT_BUTTON) {
                ARouterHelper.startActivity(RouterHub.ROUTER_SEARCH);
            }
            if (action == ThomasTitleBar.ACTION_LEFT_TEXT) {
                ARouterHelper.startActivity(RouterHub.ROUTER_SQUARE);
            }
        });

        holder = StatusHelper.getDefault().wrap(smartRefreshLayout);
        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.getNews(page);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                datas.clear();
                presenter.getNews(page);
            }
        });
        rvContent.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new NewsAdapter(datas);
        rvContent.setAdapter(adapter);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            bundle.putString("url", datas.get(position).getLink());
            ARouterHelper.startActivity(bundle, RouterHub.ROUTER_ARTICLE);
        });


    }

    private void initHead() {
        banner = (Banner) LayoutInflater.from(mActivity).inflate(R.layout.view_news_head, null, false);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ScreenUtils.getScreenWidth(), SizeUtils.dp2px(160));
        banner.setLayoutParams(params);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                ImageHelper.showSimple(imageView, (String) path);
            }
        });

        banner.isAutoPlay(true);
        banner.setDelayTime(5000);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setBannerAnimation(Transformer.Default);
    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_title_background));
        BarUtils.addMarginTopEqualStatusBarHeight(titleBar);
        holder.showLoading();
        presenter.getBanner();
        presenter.getNews(page);
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_title_background));
        BarUtils.addMarginTopEqualStatusBarHeight(titleBar);
        banner.startAutoPlay();
    }

    @Override
    protected void onUserInvisible() {
        super.onUserInvisible();
        banner.stopAutoPlay();
    }

    @Override
    protected void destroyViewAndThing() {
        super.destroyViewAndThing();
        banner.stopAutoPlay();
    }

    @Override
    public void onThomasClick(@NonNull View view) {

    }

    @Override
    public void onFailed(String failed) {
        smartRefreshLayout.finishRefresh(false);
        smartRefreshLayout.finishLoadMore(false);
        if (page == 0) {
            holder.withData(failed).withRetry(() -> presenter.getNews(page)).showLoadFailed();
        } else {
            ToastUtils.showShort(failed);
        }
    }

    @Override
    public void onBannerSuccess(List<BannerBean> datas) {
        adapter.setHeaderView(banner);
        for (BannerBean bean : datas) {
            images.add(bean.getImagePath());
            titles.add(bean.getTitle());
        }
        banner.setImages(images);
        banner.setBannerTitles(titles);
        banner.start();
    }

    @Override
    public void onBannerEmpty() {
        banner.stopAutoPlay();
        adapter.removeHeaderView(banner);
    }

    @Override
    public void onBannerFailed(String failed) {
        ToastUtils.showShort(failed);
        banner.stopAutoPlay();
        adapter.removeHeaderView(banner);
    }

    @Override
    public void onNewsSuccess(List<NewsListBean.DatasBean> datas) {
        smartRefreshLayout.finishRefresh(true);
        smartRefreshLayout.finishLoadMore(true);
        adapter.addData(datas);
        holder.showLoadSuccess();
    }

    @Override
    public void onNewsEmpty() {
        holder.showEmpty();
    }

    @Override
    public void onMoreData(boolean hasMoreData) {
        smartRefreshLayout.setNoMoreData(!hasMoreData);
    }
}
