package com.thomas.andfun.login.ui.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.login.ui.contract.RegisterContract;
import com.thomas.andfun.login.ui.model.RegisterModel;

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


}
