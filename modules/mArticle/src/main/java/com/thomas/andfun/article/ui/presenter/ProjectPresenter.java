package com.thomas.andfun.article.ui.presenter;

import com.thomas.andfun.article.bean.ProjectBean;
import com.thomas.andfun.article.ui.contract.ProjectContract;
import com.thomas.andfun.article.ui.model.ProjectModel;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/12
 * @updatelog
 * @since
 */
public class ProjectPresenter extends BaseMvpPresenter<ProjectContract.Model, ProjectContract.View> implements ProjectContract.Presenter {

    @Override
    protected ProjectContract.Model createModel() {
        return new ProjectModel();
    }

    @Override
    public void getCateProject(int id, int page) {
        getModel().getCateProject(id, page, new BaseThomasCallback<ProjectBean>() {
            @Override
            protected void onSuccess(ProjectBean succeed) {
                if (isViewAttached()) {
                    getView().onMoreData(succeed.getCurPage() < succeed.getPageCount());
                    if (succeed.getDatas() != null && succeed.getDatas().size() > 0) {
                        getView().getCateProjectSuccess(succeed.getDatas());
                    } else if (page == 0) {
                        getView().getCateProjectEmpty();
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
