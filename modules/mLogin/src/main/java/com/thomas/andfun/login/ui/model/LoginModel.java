package com.thomas.andfun.login.ui.model;

import com.thomas.andfun.login.bean.LoginBean;
import com.thomas.andfun.login.ui.contract.LoginContract;
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
public class LoginModel implements LoginContract.Model {
    @Override
    public void login(String username, String password, BaseThomasCallback<LoginBean> callback) {
        Params params = Params.newBuilder().add("username", username).add("password", password).build();
        HttpHelper.post("https://www.wanandroid.com/user/login", params, callback);
    }
}
