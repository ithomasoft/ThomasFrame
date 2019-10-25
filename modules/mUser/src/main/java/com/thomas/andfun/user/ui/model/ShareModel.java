package com.thomas.andfun.user.ui.model;

import com.thomas.andfun.user.bean.ShareListBean;
import com.thomas.andfun.user.ui.contract.ShareContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class ShareModel implements ShareContract.Model {
    @Override
    public void getShareList(int page, BaseThomasCallback<ShareListBean> callback) {
        HttpHelper.get("https://wanandroid.com/user/lg/private_articles/" + page + "/json", callback);
    }

    @Override
    public void unShare(int id, BaseThomasCallback<String> callback) {
        HttpHelper.post("https://wanandroid.com/lg/user_article/delete/" + id + "/json", callback);
    }
}
