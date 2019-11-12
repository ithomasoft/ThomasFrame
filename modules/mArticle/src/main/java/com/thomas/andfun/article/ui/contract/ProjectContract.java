package com.thomas.andfun.article.ui.contract;

import com.thomas.andfun.article.bean.ProjectBean;
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
public interface ProjectContract {
    interface Model extends IBaseMvpModel {
        void getCateProject(int id, int page, BaseThomasCallback<ProjectBean> callback);
    }

    interface View extends IBaseMvpView {
        void getCateProjectSuccess(List<ProjectBean.DatasBean> succeed);

        void getCateProjectEmpty();

        void onMoreData(boolean hasMoreData);
    }

    interface Presenter {
        void getCateProject(int id, int page);
    }
}