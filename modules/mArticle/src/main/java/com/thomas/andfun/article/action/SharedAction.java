package com.thomas.andfun.article.action;

import com.thomas.sdk.delay.Action;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.service.RouterHub;

/**
 * @author Thomas
 * @describe 跳转到分享的Action
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class SharedAction implements Action {
    @Override
    public void call() {
        ARouterHelper.startActivity(RouterHub.ROUTER_ARTICLE_SHARE);
    }
}
