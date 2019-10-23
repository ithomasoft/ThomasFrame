package com.thomas.andfun.user.ui.contract;

import com.thomas.andfun.user.bean.IntegralBean;
import com.thomas.andfun.user.bean.IntegralListBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public interface IntegralContract {
    interface Model extends IBaseMvpModel {
        void getIntegralList(int page, BaseThomasCallback<IntegralListBean> callback);

        void getMyIntegral(BaseThomasCallback<IntegralBean> callback);
    }

    interface View extends IBaseMvpView {
        void onSuccess(List<IntegralListBean.DatasBean> datas);

        void onEmpty();

        void onMoreData(boolean hasMoreData);

        void onMyIntegralSuccess(IntegralBean data);

        void onMyIntegralFailed(String failed);
    }

    interface Presenter {
        void getIntegralList(int page);

        void getMyIntegral();
    }
}