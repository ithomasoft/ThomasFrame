package com.thomas.andfun.article.ui.model;

import com.thomas.andfun.article.bean.KnowledgeBean;
import com.thomas.andfun.article.ui.contract.KnowledgeContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/12
 * @updatelog
 * @since
 */
public class KnowledgeModel implements KnowledgeContract.Model {
    @Override
    public void getKnowledge(int id, int page, BaseThomasCallback<KnowledgeBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/article/list/" + page + "/json?cid=" + id, callback);
    }
}
