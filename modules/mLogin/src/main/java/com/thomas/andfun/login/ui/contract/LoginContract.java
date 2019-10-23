package com.thomas.andfun.login.ui.contract;

import com.thomas.andfun.login.bean.LoginBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public interface LoginContract {
    interface Model extends IBaseMvpModel {
        void login(String username, String password, BaseThomasCallback<LoginBean> callback);
    }

    interface View extends IBaseMvpView {
        void onLoginSuccess(LoginBean user);
    }

    interface Presenter {
        void login(String username, String password);
    }
}