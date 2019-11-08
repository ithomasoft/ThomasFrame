package com.thomas.andfun.home.fragment.model;

import com.thomas.andfun.home.bean.ProjectCateBean;
import com.thomas.andfun.home.fragment.contract.ProjectContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class ProjectModel implements ProjectContract.Model {
    @Override
    public void getProject(BaseThomasCallback<List<ProjectCateBean>> callback) {
        HttpHelper.get("https://www.wanandroid.com/project/tree/json", callback);
    }
}
