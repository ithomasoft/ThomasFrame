package com.thomas.andfun.login.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperButton;
import com.thomas.andfun.login.R;
import com.thomas.andfun.login.R2;
import com.thomas.andfun.login.bean.LoginBean;
import com.thomas.andfun.login.ui.contract.LoginContract;
import com.thomas.andfun.login.ui.presenter.LoginPresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.BarUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.delay.SingleCall;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.DialogHelper;
import com.thomas.sdk.helper.LoadingHelper;
import com.thomas.sdk.helper.UserHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_LOGIN)
public class LoginActivity extends ThomasMvpActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R2.id.et_username)
    AppCompatEditText etUsername;
    @BindView(R2.id.et_password)
    AppCompatEditText etPassword;
    @BindView(R2.id.btn_login)
    SuperButton btnLogin;
    @BindView(R2.id.btn_forget)
    AppCompatTextView btnForget;
    @BindView(R2.id.btn_register)
    SuperButton btnRegister;

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if ((!TextUtils.isEmpty(etPassword.getText())) && (!TextUtils.isEmpty(etUsername.getText()))) {
                btnLogin.setEnabled(true);
                btnLogin.setClickable(true);
            } else {
                btnLogin.setEnabled(false);
                btnLogin.setClickable(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public void initStatusBar() {
        super.initStatusBar();
        BarUtils.setStatusBarLightMode(mActivity, true);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, R.color.thomas_color_app_background));
    }

    @Override
    public int bindLayout() {

        return R.layout.activity_login;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {

        applyThomasClickListener(btnForget, btnLogin, btnRegister);
        etUsername.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onThomasClick(@NonNull View view) {
        int clickId = view.getId();
        if (clickId == R.id.btn_login) {
            LoadingHelper.showLoading();
            presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
        }
        if (clickId == R.id.btn_forget) {
            showForgetTips();
        }
        if (clickId == R.id.btn_register) {
            ARouterHelper.startActivity(RouterHub.ROUTER_REGISTER);
        }
    }

    private void showForgetTips() {
        DialogHelper.showTipsCenter("您可以通过以下途径找回密码：发送您的用户名和邮箱到623565791@qq.com邮箱。");
    }

    @Override
    public void onFailed(String failed) {
        ToastUtils.showShort(failed);
    }

    @Override
    public void onLoginSuccess(LoginBean user) {
        UserHelper.setEditPassword(etPassword.getText().toString());
        UserHelper.setEditUsername(etUsername.getText().toString());
        UserHelper.setUsername(user.getUsername());
        UserHelper.setUserId(user.getId());
        UserHelper.setEmail(user.getEmail());
        UserHelper.setIcon(user.getIcon());
        UserHelper.setNickname(user.getNickname());
        UserHelper.setType(user.getType());
        SingleCall.getInstance().doCall();
        ActivityUtils.finishActivity(mActivity);
    }
}
