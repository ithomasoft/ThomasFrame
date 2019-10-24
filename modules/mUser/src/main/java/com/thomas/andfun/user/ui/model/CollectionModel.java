package com.thomas.andfun.user.ui.model;

import com.thomas.andfun.user.bean.CollectionListBean;
import com.thomas.andfun.user.ui.contract.CollectionContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class CollectionModel implements CollectionContract.Model {
    @Override
    public void getCollectionList(int page, BaseThomasCallback<CollectionListBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/lg/collect/list/" + page + "/json", callback);
    }
}
