package com.thomas.andfun.home.valid;

import com.thomas.sdk.delay.Valid;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.UserHelper;
import com.thomas.service.RouterHub;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class LoginValid implements Valid {
    @Override
    public boolean check() {
        return UserHelper.isLogin();
    }

    @Override
    public void doValid() {
        ARouterHelper.startActivity(RouterHub.ROUTER_LOGIN);
    }
}
