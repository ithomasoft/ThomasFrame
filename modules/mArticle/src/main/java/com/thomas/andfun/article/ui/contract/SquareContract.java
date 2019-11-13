package com.thomas.andfun.article.ui.contract;

import com.thomas.andfun.article.bean.KnowledgeBean;
import com.thomas.andfun.article.bean.SquareBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/13
 * @updatelog
 * @since
 */
public interface SquareContract {
    interface Model extends IBaseMvpModel {
        void getSquareArticle(int page, BaseThomasCallback<SquareBean> callback);
    }

    interface View extends IBaseMvpView {
        void getSquareArticleSuccess(List<SquareBean.DatasBean> succeed);

        void getSquareArticleEmpty();

        void onMoreData(boolean hasMoreData);
    }

    interface Presenter {
        void getSquareArticle(int page);
    }
}