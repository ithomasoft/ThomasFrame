package com.thomas.andfun.article.ui.contract;

import com.thomas.andfun.article.bean.KnowledgeBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/12
 * @updatelog
 * @since
 */
public interface KnowledgeContract {
    interface Model extends IBaseMvpModel {
        void getKnowledge(int id, int page, BaseThomasCallback<KnowledgeBean> callback);
    }

    interface View extends IBaseMvpView {
        void getKnowledgeSuccess(List<KnowledgeBean.DatasBean> succeed);

        void getKnowledgeEmpty();

        void onMoreData(boolean hasMoreData);
    }

    interface Presenter {
        void getKnowledge(int id, int page);
    }
}