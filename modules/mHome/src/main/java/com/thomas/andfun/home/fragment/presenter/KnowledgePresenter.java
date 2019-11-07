package com.thomas.andfun.home.fragment.presenter;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.thomas.andfun.home.bean.KnowledgeBean;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.KnowledgeContract;
import com.thomas.andfun.home.fragment.model.KnowledgeModel;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class KnowledgePresenter extends BaseMvpPresenter<KnowledgeContract.Model, KnowledgeContract.View> implements KnowledgeContract.Presenter {

    @Override
    protected KnowledgeContract.Model createModel() {
        return new KnowledgeModel();
    }


    @Override
    public void getKnowledge() {
        getModel().getKnowledge(new BaseThomasCallback<List<KnowledgeBean>>() {
            @Override
            protected void onSuccess(List<KnowledgeBean> succeed) {
                if (isViewAttached()) {
                    List<MultiItemEntity> datas = new ArrayList<>();
                    for (KnowledgeBean knowledge : succeed) {
                        if (knowledge.getVisible() == 1 && !knowledge.getName().contains("Tab")) {
                            for (int i = 0; i < knowledge.getChildren().size(); i++) {
                                KnowledgeBean.ChildrenBean childrenBean = knowledge.getChildren().get(i);
                                knowledge.addSubItem(childrenBean);
                            }
                            datas.add(knowledge);
                        }
                    }
                    if (datas.size() == 0) {
                        getView().onEmpty();
                    } else {
                        getView().getKnowledgeSuccess(datas);
                    }
                }
            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().onFailed(failed);
                }
            }
        });
    }
}
