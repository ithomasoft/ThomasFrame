package com.thomas.andfun.user.ui.model;

import com.thomas.andfun.user.bean.IntegralBean;
import com.thomas.andfun.user.ui.contract.MineContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class MineModel implements MineContract.Model {
    @Override
    public void getMyIntegral(BaseThomasCallback<IntegralBean> callback) {
        HttpHelper.get("https://www.wanandroid.com/lg/coin/userinfo/json", callback);
    }

    @Override
    public void logout(BaseThomasCallback<String> callback) {
        HttpHelper.get("https://www.wanandroid.com/user/logout/json", callback);
    }
}
