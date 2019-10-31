package com.thomas.andfun.home.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.LayoutInflaterCompat;
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
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.BarUtils;
import com.thomas.core.utils.ScreenUtils;
import com.thomas.core.utils.SizeUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.ImageHelper;
import com.thomas.sdk.helper.LoadingHelper;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.ThomasMvpFragment;
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
public class NewsFragment extends ThomasMvpFragment<NewsPresenter> implements NewsContract.View {

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
        BarUtils.setStatusBarLightMode(mActivity, true);
        return R.layout.fragment_news;
    }

    @Override
    public void onStart() {
        super.onStart();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_title_background));
        BarUtils.addMarginTopEqualStatusBarHeight(titleBar);
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        initHead();
        titleBar.setListener((view, action, extra) -> {
            ToastUtils.showShort(action + "---");
            if (action == ThomasTitleBar.ACTION_RIGHT_BUTTON) {
                ToastUtils.showShort("打开搜索页面");
            }
            if (action == ThomasTitleBar.ACTION_LEFT_TEXT) {
                ToastUtils.showShort("广场文章列表");
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
            ToastUtils.showShort(datas.get(position).getLink());
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
    public void doBusiness() {
        holder.showLoading();
        presenter.getBanner();
        presenter.getNews(page);
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
