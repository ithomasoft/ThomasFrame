package com.thomas.andfun.home.fragment.presenter;

import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.ProjectContract;
import com.thomas.andfun.home.fragment.model.ProjectModel;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class ProjectPresenter extends BaseMvpPresenter<ProjectContract.Model, ProjectContract.View> implements ProjectContract.Presenter {

    @Override
    protected ProjectContract.Model createModel() {
        return new ProjectModel();
    }


}
