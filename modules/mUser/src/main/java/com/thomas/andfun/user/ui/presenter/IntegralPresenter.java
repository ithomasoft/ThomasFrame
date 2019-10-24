package com.thomas.andfun.user.ui.presenter;

import com.thomas.andfun.user.bean.IntegralBean;
import com.thomas.andfun.user.bean.IntegralListBean;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.user.ui.contract.IntegralContract;
import com.thomas.andfun.user.ui.model.IntegralModel;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class IntegralPresenter extends BaseMvpPresenter<IntegralContract.Model, IntegralContract.View> implements IntegralContract.Presenter {

    @Override
    protected IntegralContract.Model createModel() {
        return new IntegralModel();
    }


    @Override
    public void getIntegralList(int page) {
        if (isViewAttached()) {
            getModel().getIntegralList(page, new BaseThomasCallback<IntegralListBean>() {
                @Override
                protected void onSuccess(IntegralListBean succeed) {
                    getView().onMoreData(succeed.getCurPage() < succeed.getPageCount());
                    if (succeed.getDatas() != null && succeed.getDatas().size() > 0) {
                        getView().onSuccess(succeed.getDatas());
                    } else if (page == 1) {
                        getView().onEmpty();
                    }
                }

                @Override
                protected void onFailed(String failed) {
                    getView().onFailed(failed);
                }
            });
        }
    }

    @Override
    public void getMyIntegral() {
        if (isViewAttached()) {
            getModel().getMyIntegral(new BaseThomasCallback<IntegralBean>() {
                @Override
                protected void onSuccess(IntegralBean succeed) {
                    getView().onMyIntegralSuccess(succeed);
                }

                @Override
                protected void onFailed(String failed) {
                    getView().onMyIntegralFailed(failed);
                }
            });
        }
    }
}
