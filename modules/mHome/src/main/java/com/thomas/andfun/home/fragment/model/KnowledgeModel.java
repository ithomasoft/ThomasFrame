package com.thomas.andfun.home.fragment.model;

import com.thomas.andfun.home.bean.KnowledgeBean;
import com.thomas.andfun.home.fragment.contract.KnowledgeContract;
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
public class KnowledgeModel implements KnowledgeContract.Model {
    @Override
    public void getKnowledge(BaseThomasCallback<List<KnowledgeBean>> callback) {
        HttpHelper.get("https://www.wanandroid.com/tree/json", callback);
    }
}
