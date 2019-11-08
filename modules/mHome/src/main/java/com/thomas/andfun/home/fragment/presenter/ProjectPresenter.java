package com.thomas.andfun.home.fragment.presenter;

import com.thomas.andfun.home.bean.ProjectCateBean;
import com.thomas.core.mvp.BaseMvpPresenter;
import com.thomas.andfun.home.fragment.contract.ProjectContract;
import com.thomas.andfun.home.fragment.model.ProjectModel;
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
public class ProjectPresenter extends BaseMvpPresenter<ProjectContract.Model, ProjectContract.View> implements ProjectContract.Presenter {

    @Override
    protected ProjectContract.Model createModel() {
        return new ProjectModel();
    }


    @Override
    public void getProject() {
        getModel().getProject(new BaseThomasCallback<List<ProjectCateBean>>() {
            @Override
            protected void onSuccess(List<ProjectCateBean> succeed) {
                List<ProjectCateBean> datas = new ArrayList<>();
                for (ProjectCateBean cateBean : succeed) {
                    if (cateBean.getVisible() == 1) {
                        datas.add(cateBean);
                    }
                }
                if (isViewAttached()) {
                    if (datas.size() > 0) {
                        getView().getCateSuccess(datas);
                    } else {
                        getView().getCateEmpty();
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
