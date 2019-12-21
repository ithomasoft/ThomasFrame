package com.thomas.andfun.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;

import com.allen.library.SuperTextView;
import com.thomas.andfun.home.R;
import com.thomas.andfun.home.action.MyCollectedAction;
import com.thomas.andfun.home.action.MySharedAction;
import com.thomas.andfun.home.fragment.contract.MineContract;
import com.thomas.andfun.home.fragment.presenter.MinePresenter;
import com.thomas.andfun.home.valid.LoginValid;
import com.thomas.core.constant.PermissionConstants;
import com.thomas.core.utils.BarUtils;
import com.thomas.core.utils.PermissionUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.sdk.delay.SingleCall;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.ImageHelper;
import com.thomas.sdk.helper.UserHelper;
import com.thomas.sdk.ui.LazyThomasMvpFragment;
import com.thomas.service.RouterHub;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class MineFragment extends LazyThomasMvpFragment<MinePresenter> implements MineContract.View {

    private AppCompatImageView bgMine, ivHead;
    private SuperTextView tvNickname, btnIntegral;

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
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
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        bgMine = findViewById(R.id.bg_mine);
        ivHead = findViewById(R.id.iv_head);
        btnIntegral = findViewById(R.id.btn_integral);
        tvNickname = findViewById(R.id.tv_nick_name);
        ImageHelper.showSimpleWithBlur(bgMine, R.drawable.bg_mine);
        applyThomasClickListener(findViewById(R.id.tv_nick_name), findViewById(R.id.btn_integral), findViewById(R.id.btn_collection), findViewById(R.id.btn_share)
                , findViewById(R.id.btn_scan), findViewById(R.id.btn_todo)
                , findViewById(R.id.btn_setting), findViewById(R.id.btn_about));
    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, android.R.color.transparent));

        if (UserHelper.isLogin()) {
            tvNickname.setCenterString(UserHelper.getNickname());
            btnIntegral.setVisibility(View.VISIBLE);
        } else {
            tvNickname.setCenterString("点击进行登录");
            btnIntegral.setVisibility(View.GONE);
        }
        ImageHelper.showSimpleSquare(ivHead, R.mipmap.ic_launcher_round);
    }

    @Override
    protected void onUserVisible() {
        super.onUserVisible();
        BarUtils.setStatusBarLightMode(mActivity, false);
        BarUtils.setStatusBarColor(mActivity, ContextCompat.getColor(mActivity, android.R.color.transparent));

        if (UserHelper.isLogin()) {
            tvNickname.setCenterString(UserHelper.getNickname());
            btnIntegral.setVisibility(View.VISIBLE);
        } else {
            tvNickname.setCenterString("点击进行登录");
            btnIntegral.setVisibility(View.GONE);
        }
    }

    @Override
    public void onThomasClick(@NonNull View view) {
        int clickId = view.getId();

        if (clickId == R.id.tv_nick_name) {
            if (UserHelper.isLogin()) {
                ARouterHelper.startActivity(RouterHub.ROUTER_USER);
            } else {
                ARouterHelper.startActivity(RouterHub.ROUTER_LOGIN);
            }

        }

        if (clickId == R.id.btn_integral) {
            ARouterHelper.startActivity(RouterHub.ROUTER_INTEGRAL);
        }

        if (clickId == R.id.btn_collection) {

            SingleCall.getInstance().addAction(new MyCollectedAction()).addValid(new LoginValid()).doCall();

        }
        if (clickId == R.id.btn_share) {
            SingleCall.getInstance().addAction(new MySharedAction()).addValid(new LoginValid()).doCall();

        }
        if (clickId == R.id.btn_scan) {
            PermissionUtils.permission(PermissionConstants.CAMERA).callback(new PermissionUtils.SimpleCallback() {
                @Override
                public void onGranted() {
                    ARouterHelper.startActivity(RouterHub.ROUTER_SCAN);
                }

                @Override
                public void onDenied() {

                }
            }).request();

        }
        if (clickId == R.id.btn_todo) {
            ToastUtils.showLong("待办清单");
        }
        if (clickId == R.id.btn_setting) {
            ARouterHelper.startActivity(RouterHub.ROUTER_SETTING);
        }
        if (clickId == R.id.btn_about) {
            ARouterHelper.startActivity(RouterHub.ROUTER_ABOUT);
        }
    }

    @Override
    public void onFailed(String failed) {

    }
}
