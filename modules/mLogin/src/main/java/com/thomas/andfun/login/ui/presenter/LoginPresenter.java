package com.thomas.andfun.login.ui.presenter;

import com.thomas.andfun.login.bean.LoginBean;
import com.thomas.andfun.login.ui.contract.LoginContract;
import com.thomas.andfun.login.ui.model.LoginModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class LoginPresenter extends BaseMvpPresenter<LoginContract.Model, LoginContract.View> implements LoginContract.Presenter {

    @Override
    protected LoginContract.Model createModel() {
        return new LoginModel();
    }


    @Override
    public void login(String username, String password) {

            getModel().login(username, password, new BaseThomasCallback<LoginBean>() {
                @Override
                protected void onSuccess(LoginBean succeed) {
                    if (isViewAttached()) {
                        getView().onLoginSuccess(succeed);
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

}
