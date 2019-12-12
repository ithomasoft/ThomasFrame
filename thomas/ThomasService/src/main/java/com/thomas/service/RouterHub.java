package com.thomas.service;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public interface RouterHub {
    /**
     * 启动页
     */
    String ROUTER_SPLASH = "/splash/splash";

    /**
     * 主页
     */
    String ROUTER_HOME = "/home/home";

    /**
     * 登录页
     */
    String ROUTER_LOGIN = "/login/login";
    /**
     * 注册页
     */
    String ROUTER_REGISTER = "/login/register";

    /**
     * 个人中心页
     */
    String ROUTER_USER = "/mine/mine";

    /**
     * 我的收藏页
     */
    String ROUTER_COLLECTION = "/mine/collection";

    /**
     * 我的分享页
     */
    String ROUTER_SHARE = "/mine/share";

    /**
     * 我的积分页
     */
    String ROUTER_INTEGRAL = "/mine/integral";

    /**
     * 设置中心页
     */
    String ROUTER_SETTING = "/setting/setting";
    /**
     * 关于我们页
     */
    String ROUTER_ABOUT = "/about/about";
    /**
     * 关于网站页
     */
    String ROUTER_WEB = "/about/web";
    /**
     * 积分规则页
     */
    String ROUTER_RULES = "/about/rules";

    /**
     * 文章内容页
     */
    String ROUTER_ARTICLE = "/article/detail";

    /**
     * 项目分类列表页
     */
    String ROUTER_PROJECT = "/article/project";

    /**
     * 知识体系列表页
     */
    String ROUTER_KNOWLEDGE = "/article/knowledge";

    /**
     * 广场列表页
     */
    String ROUTER_SQUARE = "/article/square";

    /**
     * 分享页
     */
    String ROUTER_ARTICLE_SHARE = "/article/share";

    /**
     * 搜索页
     */
    String ROUTER_SEARCH = "/article/search";

    /**
     * 搜索结果页
     */
    String ROUTER_RESULT = "/article/result";


    /**
     * 扫码页
     */
    String ROUTER_SCAN = "/scan/home";

}
