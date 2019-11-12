package com.thomas.andfun.article.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.DefaultWebClient;
import com.just.agentweb.WebChromeClient;
import com.just.agentweb.WebViewClient;
import com.thomas.andfun.article.R;
import com.thomas.andfun.article.R2;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.ui.ThomasActivity;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/11/11
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_ARTICLE)
public class DetailsActivity extends ThomasActivity {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.ll_web_parent)
    LinearLayoutCompat llWebParent;
    private String url;

    protected AgentWeb mAgentWeb;
    private WebChromeClient webChromeClient;
    private WebViewClient webViewClient;

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {
        url = bundle.getString("url");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_details;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });

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

    }

    private void initWebClient() {
        webViewClient = new WebViewClient() {

        };
        webChromeClient = new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                titleBar.getCenterTextView().setText(title);
            }
        };
    }

    @Override
    public void doBusiness() {

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
