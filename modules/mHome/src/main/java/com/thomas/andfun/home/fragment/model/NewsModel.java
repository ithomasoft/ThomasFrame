package com.thomas.andfun.home.fragment.model;

import com.thomas.andfun.home.bean.BannerBean;
import com.thomas.andfun.home.bean.NewsListBean;
import com.thomas.andfun.home.fragment.contract.NewsContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class NewsModel implements NewsContract.Model {
    @Override
    public void getBanner(BaseThomasCallback<List<BannerBean>> callback) {
        HttpHelper.get("https://www.wanandroid.com/banner/json", callback);
    }

    @Override
    public void getNews(int page, BaseThomasCallback<NewsListBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/article/list/" + page + "/json", callback);
    }
}
