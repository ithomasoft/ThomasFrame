package com.thomas.andfun.article.ui.model;

import com.thomas.andfun.article.ApiConstant;
import com.thomas.andfun.article.ui.contract.ShareArticleContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;
import com.yanzhenjie.kalle.Params;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/10
 * @updatelog
 * @since
 */
public class ShareArticleModel implements ShareArticleContract.Model {
    @Override
    public void submit(String title, String link, BaseThomasCallback<String> callback) {
        Params params = Params.newBuilder().add("title", title).add("link", link).build();
        HttpHelper.post("https://www.wanandroid.com/lg/user_article/add/json", params, callback);
    }
}
