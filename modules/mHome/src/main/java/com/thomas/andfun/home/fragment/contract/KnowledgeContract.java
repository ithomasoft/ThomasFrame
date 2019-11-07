package com.thomas.andfun.home.fragment.contract;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.thomas.andfun.home.bean.KnowledgeBean;
import com.thomas.core.mvp.IBaseMvpModel;
import com.thomas.core.mvp.IBaseMvpView;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public interface KnowledgeContract {
    interface Model extends IBaseMvpModel {
        void getKnowledge(BaseThomasCallback<List<KnowledgeBean>> callback);
    }

    interface View extends IBaseMvpView {
        void getKnowledgeSuccess(List<MultiItemEntity> datas);

        void onEmpty();
    }

    interface Presenter {
        void getKnowledge();
    }
}