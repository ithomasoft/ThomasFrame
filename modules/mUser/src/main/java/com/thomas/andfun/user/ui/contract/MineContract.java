package com.thomas.andfun.user.ui.contract;

import com.thomas.andfun.user.bean.IntegralBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public interface MineContract {
    interface Model extends IBaseMvpModel {
        void getMyIntegral(BaseThomasCallback<IntegralBean> callback);

        void logout(BaseThomasCallback<String> callback);
    }

    interface View extends IBaseMvpView {
        void onMyIntegralSuccess(IntegralBean data);

        void logoutSuccess();

        void logoutFailed(String failed);
    }

    interface Presenter {
        void getMyIntegral();

        void logout();
    }
}