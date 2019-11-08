package com.thomas.andfun.home.fragment.presenter;

import com.thomas.andfun.home.bean.ProjectBean;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.ProjectCateContract;
import com.thomas.andfun.home.fragment.model.ProjectCateModel;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/08
 * @updatelog
 * @since
 */
public class ProjectCatePresenter extends BaseMvpPresenter<ProjectCateContract.Model, ProjectCateContract.View> implements ProjectCateContract.Presenter {

    @Override
    protected ProjectCateContract.Model createModel() {
        return new ProjectCateModel();
    }


    @Override
    public void getCateProject(int id, int page) {
        getModel().getCateProject(id, page, new BaseThomasCallback<ProjectBean>() {
            @Override
            protected void onSuccess(ProjectBean succeed) {
                if (isViewAttached()) {
                    getView().onMoreData(succeed.getCurPage() < succeed.getTotal());
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
                    getView().onFailed(failed);
                }
            }
        });
    }
}
