package com.thomas.andfun.home.fragment.presenter;

import com.thomas.andfun.home.bean.BannerBean;
import com.thomas.andfun.home.bean.NewsListBean;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.NewsContract;
import com.thomas.andfun.home.fragment.model.NewsModel;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class NewsPresenter extends BaseMvpPresenter<NewsContract.Model, NewsContract.View> implements NewsContract.Presenter {

    @Override
    protected NewsContract.Model createModel() {
        return new NewsModel();
    }


    @Override
    public void getBanner() {

        getModel().getBanner(new BaseThomasCallback<List<BannerBean>>() {
            @Override
            protected void onSuccess(List<BannerBean> succeed) {
                if (isViewAttached()) {
                    if (succeed != null && succeed.size() > 0) {
                        getView().onBannerSuccess(succeed);
                    } else {
                        getView().onBannerEmpty();
                    }
                }
            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().onBannerFailed(failed);
                }
            }
        });

    }

    @Override
    public void getNews(int page) {

        getModel().getNews(page, new BaseThomasCallback<NewsListBean>() {
            @Override
            protected void onSuccess(NewsListBean succeed) {
                if (isViewAttached()) {
                    getView().onMoreData(succeed.getCurPage() < succeed.getPageCount());
                    if (succeed.getDatas() != null && succeed.getDatas().size() > 0) {
                        getView().onNewsSuccess(succeed.getDatas());
                    } else if (page == 0) {
                        getView().onNewsEmpty();
                    }
                }
            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().onFailed(failed);
                }
            }
        });
    }
}
