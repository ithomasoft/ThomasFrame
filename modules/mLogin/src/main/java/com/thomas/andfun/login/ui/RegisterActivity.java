package com.thomas.andfun.login.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperButton;
import com.thomas.andfun.login.R;
import com.thomas.andfun.login.R2;
import com.thomas.andfun.login.bean.LoginBean;
import com.thomas.andfun.login.ui.contract.RegisterContract;
import com.thomas.andfun.login.ui.presenter.RegisterPresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.delay.SingleCall;
import com.thomas.sdk.helper.LoadingHelper;
import com.thomas.sdk.helper.UserHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_REGISTER)
public class RegisterActivity extends ThomasMvpActivity<RegisterPresenter> implements RegisterContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.et_username)
    AppCompatEditText etUsername;
    @BindView(R2.id.et_password)
    AppCompatEditText etPassword;
    @BindView(R2.id.et_repassword)
    AppCompatEditText etRepassword;
    @BindView(R2.id.btn_register)
    SuperButton btnRegister;


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if ((!TextUtils.isEmpty(etPassword.getText())) && (!TextUtils.isEmpty(etUsername.getText()))
                    && (!TextUtils.isEmpty(etRepassword.getText()))) {
                btnRegister.setEnabled(true);
                btnRegister.setClickable(true);
            } else {
                btnRegister.setEnabled(false);
                btnRegister.setClickable(false);
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };


    @Override
    protected RegisterPresenter createPresenter() {
        return new RegisterPresenter();
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
        return R.layout.activity_register;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });

        applyThomasClickListener(btnRegister);
        etUsername.addTextChangedListener(textWatcher);
        etPassword.addTextChangedListener(textWatcher);
        etRepassword.addTextChangedListener(textWatcher);
    }

    @Override
    public void doBusiness() {

    }


    @Override
    public void onThomasClick(@NonNull View view) {
        int clickId = view.getId();
        if (clickId == R.id.btn_register) {
            LoadingHelper.showLoading();
            presenter.register(etUsername.getText().toString(), etPassword.getText().toString(), etRepassword.getText().toString());
        }
    }


    @Override
    public void onFailed(String failed) {
        ToastUtils.showShort(failed);
    }

    @Override
    public void onSuccess(LoginBean user) {
        UserHelper.setEditPassword(etPassword.getText().toString());
        UserHelper.setEditUsername(etUsername.getText().toString());
        UserHelper.setUsername(user.getUsername());
        UserHelper.setUserId(user.getId());
        UserHelper.setEmail(user.getEmail());
        UserHelper.setIcon(user.getIcon());
        UserHelper.setNickname(user.getNickname());
        UserHelper.setType(user.getType());
        SingleCall.getInstance().doCall();
        ActivityUtils.finishActivity(LoginActivity.class);
        ActivityUtils.finishActivity(mActivity);
    }
}
