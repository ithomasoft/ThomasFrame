package com.thomas.andfun.article.ui.model;

import com.thomas.andfun.article.bean.ProjectBean;
import com.thomas.andfun.article.ui.contract.ProjectContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/12
 * @updatelog
 * @since
 */
public class ProjectModel implements ProjectContract.Model {
    @Override
    public void getCateProject(int id, int page, BaseThomasCallback<ProjectBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/project/list/" + page + "/json?cid=" + id, callback);
    }
}
