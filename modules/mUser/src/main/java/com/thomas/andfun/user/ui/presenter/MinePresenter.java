package com.thomas.andfun.user.ui.presenter;

import com.thomas.andfun.user.bean.IntegralBean;
import com.thomas.andfun.user.ui.contract.MineContract;
import com.thomas.andfun.user.ui.model.MineModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class MinePresenter extends BaseMvpPresenter<MineContract.Model, MineContract.View> implements MineContract.Presenter {

    @Override
    protected MineContract.Model createModel() {
        return new MineModel();
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
                    getView().onFailed(failed);
                }
            });
        }
    }

    @Override
    public void logout() {
        if (isViewAttached()) {
            getModel().logout(new BaseThomasCallback<String>() {
                @Override
                protected void onSuccess(String succeed) {
                    getView().logoutSuccess();
                }

                @Override
                protected void onFailed(String failed) {
                    getView().logoutFailed(failed);
                }
            });
        }
    }
}
