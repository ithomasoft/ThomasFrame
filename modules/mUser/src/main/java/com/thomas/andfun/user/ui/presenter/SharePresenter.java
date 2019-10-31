package com.thomas.andfun.user.ui.presenter;

import com.thomas.andfun.user.bean.ShareListBean;
import com.thomas.andfun.user.ui.contract.ShareContract;
import com.thomas.andfun.user.ui.model.ShareModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class SharePresenter extends BaseMvpPresenter<ShareContract.Model, ShareContract.View> implements ShareContract.Presenter {

    @Override
    protected ShareContract.Model createModel() {
        return new ShareModel();
    }


    @Override
    public void getShareList(int page) {

        getModel().getShareList(page, new BaseThomasCallback<ShareListBean>() {
            @Override
            protected void onSuccess(ShareListBean succeed) {
                if (isViewAttached()) {
                    getView().onMoreData(succeed.getShareArticles().getCurPage() < succeed.getShareArticles().getPageCount());
                    if (succeed.getShareArticles().getDatas() != null && succeed.getShareArticles().getDatas().size() > 0) {
                        getView().onSuccess(succeed.getShareArticles().getDatas());
                    } else if (page == 1) {
                        getView().onEmpty();
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

    @Override
    public void unShare(int position, int id) {

        getModel().unShare(id, new BaseThomasCallback<String>() {
            @Override
            protected void onSuccess(String succeed) {
                if (isViewAttached()) {
                    getView().onUnShareSuccess(position);
                }
            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().onUnShareFailed(failed);
                }
            }
        });
    }
}
