package com.thomas.andfun.user.ui.model;

import com.thomas.andfun.user.bean.IntegralBean;
import com.thomas.andfun.user.bean.IntegralListBean;
import com.thomas.andfun.user.ui.contract.IntegralContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/23
 * @updatelog
 * @since
 */
public class IntegralModel implements IntegralContract.Model {
    @Override
    public void getIntegralList(int page, BaseThomasCallback<IntegralListBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/lg/coin/list/" + page + "/json", callback);
    }

    @Override
    public void getMyIntegral(BaseThomasCallback<IntegralBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/lg/coin/userinfo/json", callback);
    }
}
