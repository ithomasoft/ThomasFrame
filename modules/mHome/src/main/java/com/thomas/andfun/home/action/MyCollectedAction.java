package com.thomas.andfun.home.action;

import com.thomas.core.delay.Action;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.service.RouterHub;

/**
 * @author Thomas
 * @describe 跳转到我的收藏页面的Action
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class MyCollectedAction implements Action {
    @Override
    public void call() {
        ARouterHelper.startActivity(RouterHub.ROUTER_COLLECTION);
    }
}
