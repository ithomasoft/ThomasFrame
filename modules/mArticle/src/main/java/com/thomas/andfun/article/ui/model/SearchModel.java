package com.thomas.andfun.article.ui.model;

import com.thomas.andfun.article.bean.HotKeyBean;
import com.thomas.andfun.article.ui.contract.SearchContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

import java.util.List;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/13
 * @updatelog
 * @since
 */
public class SearchModel implements SearchContract.Model {
    @Override
    public void getHotKey(BaseThomasCallback<List<HotKeyBean>> callback) {
        HttpHelper.get("https://www.wanandroid.com//hotkey/json", callback);
    }
}
