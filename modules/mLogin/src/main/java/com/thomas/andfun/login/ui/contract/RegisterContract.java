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
public interface RegisterContract {
    interface Model extends IBaseMvpModel {
        void register(String username, String password, String repassword, BaseThomasCallback<LoginBean> callback);
    }

    interface View extends IBaseMvpView {
        void onSuccess(LoginBean data);
    }

    interface Presenter {
        void register(String username, String password, String repassword);
    }
}