package com.thomas.andfun.login.ui.model;

import com.thomas.andfun.login.bean.LoginBean;
import com.thomas.andfun.login.ui.contract.RegisterContract;
import com.thomas.sdk.helper.HttpHelper;
import com.thomas.sdk.kalle.BaseThomasCallback;
import com.yanzhenjie.kalle.Params;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class RegisterModel implements RegisterContract.Model {
    @Override
    public void register(String username, String password, String repassword, BaseThomasCallback<LoginBean> callback) {
        Params params = Params.newBuilder().add("username", username).add("password", password)
                .add("repassword", repassword).build();
        HttpHelper.post("https://www.wanandroid.com/user/register", params, callback);
    }
}
