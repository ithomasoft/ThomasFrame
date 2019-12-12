package com.thomas.andfun.article.ui.contract;

import com.thomas.andfun.article.bean.ResultBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
public interface ResultContract {
    interface Model extends IBaseMvpModel {
        void getData(int pageNo, String key, BaseThomasCallback<ResultBean> callback);
    }

    interface View extends IBaseMvpView {
        void getDataSuccess(List<ResultBean.DatasBean> succeed);

        void getDataEmpty();

        void onMoreData(boolean hasMoreData);
    }

    interface Presenter {
        void getData(int pageNo, String key);
    }
}