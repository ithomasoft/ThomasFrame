package com.thomas.andfun.article.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperButton;
import com.thomas.andfun.article.R;
import com.thomas.andfun.article.R2;
import com.thomas.andfun.article.ui.contract.ShareArticleContract;
import com.thomas.andfun.article.ui.presenter.ShareArticlePresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.DialogHelper;
import com.thomas.sdk.helper.LoadingHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/10
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_ARTICLE_SHARE)
public class ShareArticleActivity extends ThomasMvpActivity<ShareArticlePresenter> implements ShareArticleContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.et_title)
    AppCompatEditText etTitle;
    @BindView(R2.id.et_link)
    AppCompatEditText etLink;
    @BindView(R2.id.btn_share)
    SuperButton btnShare;

    @Override
    protected ShareArticlePresenter createPresenter() {
        return new ShareArticlePresenter();
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
        return R.layout.activity_share_article;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
            if (action == ThomasTitleBar.ACTION_RIGHT_BUTTON) {
                showTips();
            }
        });

        applyThomasClickListener(btnShare);
        etTitle.addTextChangedListener(textWatcher);
        etLink.addTextChangedListener(textWatcher);
    }

    private void showTips() {
        DialogHelper.showTipsCenter("1. 只要是任何好文都可以分享哈，并不一定要是原创！投递的文章会进入广场 tab;\n" +
                "2. CSDN，掘金，简书等官方博客站点会直接通过，不需要审核;\n" +
                "3. 其他个人站点会进入审核阶段，不要投递任何无效链接，测试的请尽快删除，否则可能会对你的账号产生一定影响;\n" +
                "4. 目前处于测试阶段，如果你发现500等错误，可以向我提交日志，让我们一起使网站变得更好。\n" +
                "5. 由于本站只有我一个人开发与维护，会尽力保证24小时内审核，当然有可能哪天太累，会延期，请保持佛系...");
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if ((!TextUtils.isEmpty(etTitle.getText())) && (!TextUtils.isEmpty(etLink.getText()))) {
                btnShare.setEnabled(true);
                btnShare.setClickable(true);
            } else {
                btnShare.setEnabled(false);
                btnShare.setClickable(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    public void doBusiness() {

    }

    @Override
    public void onFailed(Object tag, String failed) {
        ToastUtils.showShort(failed);
    }

    @Override
    public void onThomasClick(@NonNull View view) {
        int clickId = view.getId();
        if (clickId == R.id.btn_share) {
            LoadingHelper.showLoading();
            presenter.submit(etTitle.getText().toString(), etLink.getText().toString());
        }
    }

    @Override
    public void submitSuccess(String succeed) {
        ActivityUtils.finishActivity(mActivity);
    }
}
