package com.thomas.andfun.login.ui.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.login.ui.contract.LoginContract;
import com.thomas.andfun.login.ui.model.LoginModel;

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


}
