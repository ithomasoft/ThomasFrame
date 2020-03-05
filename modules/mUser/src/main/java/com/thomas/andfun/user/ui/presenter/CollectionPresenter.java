package com.thomas.andfun.user.ui.presenter;

import com.thomas.andfun.user.bean.CollectionListBean;
import com.thomas.andfun.user.ui.contract.CollectionContract;
import com.thomas.andfun.user.ui.model.CollectionModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

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


    @Override
    public void getCollectionList(int page) {
            getModel().getCollectionList(page, new BaseThomasCallback<CollectionListBean>() {
                @Override
                protected void onSuccess(CollectionListBean succeed) {
                    if (isViewAttached()) {
                        getView().onMoreData(succeed.getCurPage() < succeed.getPageCount());
                        if (succeed.getDatas() != null && succeed.getDatas().size() > 0) {
                            getView().onSuccess(succeed.getDatas());
                        } else if (page == 0) {
                            getView().onEmpty();
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

    @Override
    public void unCollection(int position, int id, int originId) {
        getModel().unCollection(id, originId, new BaseThomasCallback<String>(false) {
                @Override
                protected void onSuccess(String succeed) {
                    if (isViewAttached()) {
                        getView().onUnCollectionSuccess(position);
                    }
                }

                @Override
                protected void onFailed(String failed) {
                    if (isViewAttached()) {
                        getView().onUnCollectionFailed(failed);
                    }
                }
            });
    }
}
