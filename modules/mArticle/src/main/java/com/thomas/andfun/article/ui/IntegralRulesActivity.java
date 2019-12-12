package com.thomas.andfun.article.ui;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.andfun.article.R;
import com.thomas.andfun.article.R2;
import com.thomas.andfun.article.ui.contract.IntegralRulesContract;
import com.thomas.andfun.article.ui.presenter.IntegralRulesPresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_RULES_INTEGRAL)
public class IntegralRulesActivity extends ThomasMvpActivity<IntegralRulesPresenter> implements IntegralRulesContract.View {


    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.web_content)
    WebView webContent;

    @Override
    protected IntegralRulesPresenter createPresenter() {
        return new IntegralRulesPresenter();
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_integral_rules;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
        holder = StatusHelper.getDefault().wrap(webContent).withRetry(new Runnable() {
            @Override
            public void run() {
                holder.showLoading();
                presenter.getContent();
            }
        });

        //支持javascript
        webContent.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webContent.getSettings().setSupportZoom(false);
        // 设置出现缩放工具
        webContent.getSettings().setBuiltInZoomControls(false);
        //扩大比例的缩放
        webContent.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webContent.getSettings().setLoadWithOverviewMode(true);
    }

    @Override
    public void doBusiness() {
        holder.showLoading();
        presenter.getContent();
    }

    @Override
    public void onFailed(String failed) {
        holder.withData(failed).showLoadFailed();
    }

    @Override
    public void getContentSuccess(String content) {
        holder.showLoadSuccess();
        webContent.loadDataWithBaseURL("https://wanandroid.com/", content, "text/html;charset=utf-8", "utf-8", "");
    }
}
