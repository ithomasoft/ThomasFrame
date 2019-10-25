package com.thomas.andfun.user.ui.contract;

import com.thomas.andfun.user.bean.ShareListBean;
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
public interface ShareContract {
    interface Model extends IBaseMvpModel {
        void getShareList(int page, BaseThomasCallback<ShareListBean> callback);

        void unShare(int id, BaseThomasCallback<String> callback);

    }

    interface View extends IBaseMvpView {
        void onSuccess(List<ShareListBean.ShareArticlesBean.DatasBean> datas);

        void onEmpty();

        void onMoreData(boolean hasMoreData);

        void onUnShareSuccess(int position);

        void onUnShareFailed(String failed);
    }

    interface Presenter {
        void getShareList(int page);

        void unShare(int position, int id);

    }
}