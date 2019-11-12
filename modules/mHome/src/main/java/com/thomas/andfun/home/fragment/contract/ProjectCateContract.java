package com.thomas.andfun.home.fragment.contract;

import com.thomas.andfun.home.bean.ProjectCateBean;
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
public interface ProjectCateContract {
    interface Model extends IBaseMvpModel {
        void getProject(BaseThomasCallback<List<ProjectCateBean>> callback);
    }


    interface View extends IBaseMvpView {
        void getCateSuccess(List<ProjectCateBean> datas);

        void getCateEmpty();

    }

    interface Presenter {
        void getProject();
    }
}