package com.thomas.andfun.article.ui.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.article.ui.contract.SearchContract;
import com.thomas.andfun.article.ui.model.SearchModel;

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


}
