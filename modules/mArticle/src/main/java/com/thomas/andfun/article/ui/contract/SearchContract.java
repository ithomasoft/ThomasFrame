package com.thomas.andfun.article.ui.contract;

import com.thomas.andfun.article.bean.HotKeyBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/13
 * @updatelog
 * @since
 */
public interface SearchContract {
    interface Model extends IBaseMvpModel {
        void getHotKey(BaseThomasCallback<List<HotKeyBean>> callback);
    }

    interface View extends IBaseMvpView {
        void getHotKeySuccess(List<HotKeyBean> succeed);

        void getHotKeyFailed(String failed);
    }

    interface Presenter {
        void getHotKey();
    }
}