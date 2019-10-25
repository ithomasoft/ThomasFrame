package com.thomas.andfun.home.fragment.contract;

import com.thomas.andfun.home.bean.BannerBean;
import com.thomas.andfun.home.bean.NewsListBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public interface NewsContract {
    interface Model extends IBaseMvpModel {
        void getBanner(BaseThomasCallback<List<BannerBean>> callback);

        void getNews(int page, BaseThomasCallback<NewsListBean> callback);
    }

    interface View extends IBaseMvpView {
        void onBannerSuccess(List<BannerBean> datas);

        void onBannerEmpty();

        void onBannerFailed(String failed);

        void onNewsSuccess(List<NewsListBean.DatasBean> datas);

        void onNewsEmpty();

        void onMoreData(boolean hasMoreData);
    }

    interface Presenter {
        void getBanner();

        void getNews(int page);
    }
}