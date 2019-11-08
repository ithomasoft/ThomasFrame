package com.thomas.andfun.home.fragment.model;

import com.thomas.andfun.home.bean.ProjectBean;
import com.thomas.andfun.home.fragment.contract.ProjectCateContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/08
 * @updatelog
 * @since
 */
public class ProjectCateModel implements ProjectCateContract.Model {
    @Override
    public void getCateProject(int id, int page, BaseThomasCallback<ProjectBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/project/list/" + page + "/json?cid=" + id, callback);
    }
}
