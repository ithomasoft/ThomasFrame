package com.thomas.andfun.user.ui.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.user.ui.contract.CollectionContract;
import com.thomas.andfun.user.ui.model.CollectionModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class CollectionPresenter extends BaseMvpPresenter<CollectionContract.Model, CollectionContract.View> implements CollectionContract.Presenter {

    @Override
    protected CollectionContract.Model createModel() {
        return new CollectionModel();
    }


}
