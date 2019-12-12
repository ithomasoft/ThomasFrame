package com.thomas.andfun.article.ui.model;

import com.thomas.andfun.article.bean.ResultBean;
import com.thomas.andfun.article.ui.contract.ResultContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;
import com.yanzhenjie.kalle.Params;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
public class ResultModel implements ResultContract.Model {
    @Override
    public void getData(int pageNo, String key, BaseThomasCallback<ResultBean> callback) {
        Params params = Params.newBuilder().add("k", key).build();
        HttpHelper.post("https://www.wanandroid.com/article/query/" + pageNo + "/json", params, callback);
    }
}
