package com.thomas.andfun.login.ui.presenter;

import com.thomas.andfun.login.bean.LoginBean;
import com.thomas.andfun.login.ui.contract.RegisterContract;
import com.thomas.andfun.login.ui.model.RegisterModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class RegisterPresenter extends BaseMvpPresenter<RegisterContract.Model, RegisterContract.View> implements RegisterContract.Presenter {

    @Override
    protected RegisterContract.Model createModel() {
        return new RegisterModel();
    }


    @Override
    public void register(String username, String password, String repassword) {

        getModel().register(username, password, repassword, new BaseThomasCallback<LoginBean>() {
            @Override
            protected void onSuccess(LoginBean succeed) {
                if (isViewAttached()) {
                    getView().onSuccess(succeed);
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
