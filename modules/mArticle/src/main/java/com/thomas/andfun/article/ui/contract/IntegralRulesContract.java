package com.thomas.andfun.article.ui.contract;

import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
public interface IntegralRulesContract {
    interface Model extends IBaseMvpModel {
        void getContent(BaseThomasCallback<String> callback);
    }

    interface View extends IBaseMvpView {
        void getContentSuccess(String content);
    }

    interface Presenter {
        void getContent();

    }
}