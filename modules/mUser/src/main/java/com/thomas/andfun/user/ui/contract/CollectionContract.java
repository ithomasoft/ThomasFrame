package com.thomas.andfun.user.ui.contract;

import com.thomas.andfun.user.bean.CollectionListBean;
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
public interface CollectionContract {
    interface Model extends IBaseMvpModel {
        void getCollectionList(int page, BaseThomasCallback<CollectionListBean> callback);

    }

    interface View extends IBaseMvpView {
        void onSuccess(List<CollectionListBean.DatasBean> datas);

        void onEmpty();

        void onMoreData(boolean hasMoreData);
    }

    interface Presenter {
        void getCollectionList(int page);

    }
}