package com.thomas.andfun.article.ui.model;

import com.thomas.andfun.article.ui.contract.IntegralRulesContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
public class IntegralRulesModel implements IntegralRulesContract.Model {
    @Override
    public void getContent(BaseThomasCallback<String> callback) {
        HttpHelper.get("https://wanandroid.com/blog/show/2653", callback);
    }
}
