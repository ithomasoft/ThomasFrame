package com.thomas.andfun.home.fragment.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.NewsContract;
import com.thomas.andfun.home.fragment.model.NewsModel;

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


}
