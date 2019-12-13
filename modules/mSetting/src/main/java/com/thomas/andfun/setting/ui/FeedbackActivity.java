package com.thomas.andfun.setting.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.thomas.andfun.setting.R;
import com.thomas.andfun.setting.R2;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.AppUtils;
import com.thomas.core.utils.DeviceUtils;
import com.thomas.core.utils.NetworkUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.UserHelper;
import com.thomas.sdk.ui.ThomasActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/13
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_FEEDBACK)
public class FeedbackActivity extends ThomasActivity {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.ll_web_parent)
    LinearLayoutCompat llWebParent;

    protected AgentWeb mAgentWeb;
    private WebChromeClient webChromeClient;
    private WebViewClient webViewClient;

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                if (!mAgentWeb.back()) {
                    ActivityUtils.finishActivity(mActivity);
                }
            }
        });

        String url = "https://support.qq.com/product/108662";
        initWebClient();
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llWebParent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(getColor(R.color.thomas_color_app_title_background))
                .closeWebViewClientHelper()
                .setWebViewClient(webViewClient)
                .setWebChromeClient(webChromeClient)
                .setMainFrameErrorView(R.layout.view_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.DISALLOW)
                .interceptUnkownUrl()
                .createAgentWeb()
                .ready()
                .go(url);


        String postData = "clientInfo=" + DeviceUtils.getManufacturer() + "-" + DeviceUtils.getModel()
                + "&clientVersion=" + AppUtils.getAppVersionName()
                + "&os=" + DeviceUtils.getSDKVersionName()
                + "&osVersion=" + DeviceUtils.getSDKVersionCode()
                + "&netType=" + NetworkUtils.getNetworkType().name()
                + "&imei=" + DeviceUtils.getUniqueDeviceId()
                + "&nickname=" + UserHelper.getNickname()
                + "&avatar=" + "https://wanandroid.com/resources/image/pc/logo.png"
                + "&openid=" + UserHelper.getUserId();
        mAgentWeb.getUrlLoader().postUrl(url, postData.getBytes());
    }

    @Override
    public void doBusiness() {

    }

    private void initWebClient() {
        webViewClient = new WebViewClient() {

        };
        webChromeClient = new WebChromeClient() {

        };
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
