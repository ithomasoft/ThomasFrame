package com.thomas.andfun.article.ui.presenter;

import com.thomas.andfun.article.bean.HotKeyBean;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.article.ui.contract.SearchContract;
import com.thomas.andfun.article.ui.model.SearchModel;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/13
 * @updatelog
 * @since
 */
public class SearchPresenter extends BaseMvpPresenter<SearchContract.Model, SearchContract.View> implements SearchContract.Presenter {

    @Override
    protected SearchContract.Model createModel() {
        return new SearchModel();
    }


    @Override
    public void getHotKey() {
        getModel().getHotKey(new BaseThomasCallback<List<HotKeyBean>>(false) {
            @Override
            protected void onSuccess(List<HotKeyBean> succeed) {
                if (isViewAttached()) {
                    if (succeed != null && succeed.size() > 0) {
                        getView().getHotKeySuccess(succeed);
                    }
                }
            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().getHotKeyFailed(failed);
                }
            }
        });
    }
}
