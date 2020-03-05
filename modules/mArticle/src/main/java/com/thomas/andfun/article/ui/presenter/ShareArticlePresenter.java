package com.thomas.andfun.article.ui.presenter;

import com.thomas.andfun.article.ui.contract.ShareArticleContract;
import com.thomas.andfun.article.ui.model.ShareArticleModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/10
 * @updatelog
 * @since
 */
public class ShareArticlePresenter extends BaseMvpPresenter<ShareArticleContract.Model, ShareArticleContract.View> implements ShareArticleContract.Presenter {

    @Override
    protected ShareArticleContract.Model createModel() {
        return new ShareArticleModel();
    }


    @Override
    public void submit(String title, String link) {
        getModel().submit(title, link, new BaseThomasCallback<String>() {
            @Override
            protected void onSuccess(String succeed) {
                if (isViewAttached()) {
                    getView().submitSuccess(succeed);
                }
            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().onFailed(0, failed);
                }
            }
        });
    }
}
