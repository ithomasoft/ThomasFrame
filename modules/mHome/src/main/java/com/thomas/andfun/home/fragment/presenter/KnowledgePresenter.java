package com.thomas.andfun.home.fragment.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.KnowledgeContract;
import com.thomas.andfun.home.fragment.model.KnowledgeModel;

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


}
