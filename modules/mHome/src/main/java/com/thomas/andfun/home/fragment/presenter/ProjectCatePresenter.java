package com.thomas.andfun.home.fragment.presenter;

import com.thomas.andfun.home.bean.ProjectCateBean;
import com.thomas.andfun.home.fragment.contract.ProjectCateContract;
import com.thomas.andfun.home.fragment.model.ProjectCateModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class ProjectCatePresenter extends BaseMvpPresenter<ProjectCateContract.Model, ProjectCateContract.View> implements ProjectCateContract.Presenter {

    @Override
    protected ProjectCateContract.Model createModel() {
        return new ProjectCateModel();
    }


    @Override
    public void getProject() {
        getModel().getProject(new BaseThomasCallback<List<ProjectCateBean>>() {
            @Override
            protected void onSuccess(List<ProjectCateBean> succeed) {
                if (isViewAttached()) {
                    if (succeed.size() > 0) {
                        getView().getCateSuccess(succeed);
                    } else {
                        getView().getCateEmpty();
                    }
                }
            }

            @Override
            protected void onFailed(String failed) {
                if (isViewAttached()) {
                    getView().onFailed(0, failed);
                }
            }
        });
    }
}
