package com.thomas.andfun.article.ui.presenter;

import com.thomas.andfun.article.bean.SquareBean;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.article.ui.contract.SquareContract;
import com.thomas.andfun.article.ui.model.SquareModel;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/13
 * @updatelog
 * @since
 */
public class SquarePresenter extends BaseMvpPresenter<SquareContract.Model, SquareContract.View> implements SquareContract.Presenter {

    @Override
    protected SquareContract.Model createModel() {
        return new SquareModel();
    }


    @Override
    public void getSquareArticle(int page) {
        getModel().getSquareArticle(page, new BaseThomasCallback<SquareBean>() {
            @Override
            protected void onSuccess(SquareBean succeed) {
                if (isViewAttached()) {
                    getView().onMoreData(succeed.getCurPage() < succeed.getPageCount());
                    if (succeed.getDatas() != null && succeed.getDatas().size() > 0) {
                        getView().getSquareArticleSuccess(succeed.getDatas());
                    } else if (page == 0) {
                        getView().getSquareArticleEmpty();
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
