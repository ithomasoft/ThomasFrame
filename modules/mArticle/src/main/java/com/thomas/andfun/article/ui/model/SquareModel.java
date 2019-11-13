package com.thomas.andfun.article.ui.model;

import com.thomas.andfun.article.bean.SquareBean;
import com.thomas.andfun.article.ui.contract.SquareContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/13
 * @updatelog
 * @since
 */
public class SquareModel implements SquareContract.Model {
    @Override
    public void getSquareArticle(int page, BaseThomasCallback<SquareBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/user_article/list/" + page + "/json", callback);
    }
}
