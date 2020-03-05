package com.thomas.andfun.article.ui.presenter;

import com.thomas.andfun.article.bean.KnowledgeBean;
import com.thomas.andfun.article.ui.contract.KnowledgeContract;
import com.thomas.andfun.article.ui.model.KnowledgeModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/12
 * @updatelog
 * @since
 */
public class KnowledgePresenter extends BaseMvpPresenter<KnowledgeContract.Model, KnowledgeContract.View> implements KnowledgeContract.Presenter {

    @Override
    protected KnowledgeContract.Model createModel() {
        return new KnowledgeModel();
    }


    @Override
    public void getKnowledge(int id, int page) {
        getModel().getKnowledge(id, page, new BaseThomasCallback<KnowledgeBean>() {
            @Override
            protected void onSuccess(KnowledgeBean succeed) {
                if (isViewAttached()) {
                    getView().onMoreData(succeed.getCurPage() < succeed.getPageCount());
                    if (succeed.getDatas() != null && succeed.getDatas().size() > 0) {
                        getView().getKnowledgeSuccess(succeed.getDatas());
                    } else if (page == 0) {
                        getView().getKnowledgeEmpty();
                    }
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
