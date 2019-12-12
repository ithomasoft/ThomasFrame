package com.thomas.andfun.article.ui.contract;

import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/10
 * @updatelog
 * @since
 */
public interface ShareArticleContract {
    interface Model extends IBaseMvpModel {
        void submit(String title, String link, BaseThomasCallback<String> callback);
    }

    interface View extends IBaseMvpView {
        void submitSuccess(String succeed);
    }

    interface Presenter {
        void submit(String title, String link);
    }
}