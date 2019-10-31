package com.thomas.andfun.user.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperButton;
import com.allen.library.SuperTextView;
import com.thomas.andfun.user.R;
import com.thomas.andfun.user.R2;
import com.thomas.andfun.user.bean.IntegralBean;
import com.thomas.andfun.user.ui.contract.MineContract;
import com.thomas.andfun.user.ui.presenter.MinePresenter;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.res.dialog.NormalDialog;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.helper.DialogHelper;
import com.thomas.sdk.helper.StatusHelper;
import com.thomas.sdk.helper.UserHelper;
import com.thomas.sdk.ui.ThomasMvpActivity;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_USER)
public class MineActivity extends ThomasMvpActivity<MinePresenter> implements MineContract.View {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.tv_user)
    SuperTextView tvUser;
    @BindView(R2.id.tv_rank)
    SuperTextView tvRank;
    @BindView(R2.id.btn_logout)
    SuperButton btnLogout;

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
        return R.layout.activity_mine;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
        holder = StatusHelper.getDefault().wrap(findViewById(R.id.cl_user));

        tvUser.setLeftString(UserHelper.getNickname());
        tvUser.setLeftBottomString(UserHelper.getUsername());
        tvRank.setLeftTextIsBold(true);
        tvRank.setCenterTextIsBold(true);
        tvRank.setRightTextIsBold(true);
        applyThomasClickListener(btnLogout);
    }

    @Override
    public void doBusiness() {
        holder.showLoading();
        presenter.getMyIntegral();
    }

    @Override
    public void onFailed(String failed) {
        holder.withData(failed).showLoadFailed();
    }

    @Override
    public void onMyIntegralSuccess(IntegralBean data) {
        holder.showLoadSuccess();
        tvRank.setLeftString(data.getCoinCount() + "");
        tvRank.setCenterString(data.getRank() + "");
        tvRank.setRightString("Lv" + (data.getCoinCount() / 100 + 1));
    }

    @Override
    public void logoutSuccess() {
        UserHelper.cleanUserInfo();
        ActivityUtils.finishActivity(mActivity);
    }

    @Override
    public void logoutFailed(String failed) {
        ToastUtils.showShort(failed);
    }

    @Override
    public void onThomasClick(@NonNull View view) {
        int clickId = view.getId();
        if (clickId == R.id.btn_logout) {

            logout();

        }
    }

    private void logout() {
        DialogHelper.showDialogCenter("退出登录", "确定要退出登录吗?", new NormalDialog.OnDialogListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onSure() {
                presenter.logout();
            }
        });
    }
}
