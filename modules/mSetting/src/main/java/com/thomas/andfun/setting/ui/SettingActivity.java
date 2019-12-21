package com.thomas.andfun.setting.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.allen.library.SuperTextView;
import com.thomas.andfun.setting.R;
import com.thomas.andfun.setting.R2;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.CleanUtils;
import com.thomas.core.utils.FileUtils;
import com.thomas.core.utils.PathUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.core.utils.Utils;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.LoadingHelper;
import com.thomas.sdk.helper.UpdateHelper;
import com.thomas.sdk.ui.ThomasActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/25
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_SETTING)
public class SettingActivity extends ThomasActivity {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.btn_update)
    SuperTextView btnUpdate;
    @BindView(R2.id.btn_clean)
    SuperTextView btnClean;
    @BindView(R2.id.btn_feedback)
    SuperTextView btnFeedback;

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
        applyThomasClickListener(btnUpdate, btnClean, btnFeedback);
        String cacheSize = FileUtils.getSize(PathUtils.getInternalAppCachePath());
        btnClean.setRightString(cacheSize);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onThomasClick(@NonNull View view) {
        int clickId = view.getId();
        if (clickId == R.id.btn_feedback) {
            ARouterHelper.startActivity(RouterHub.ROUTER_FEEDBACK);
        }

        if (clickId == R.id.btn_update) {
            UpdateHelper.checkUpdate();
        }

        if (clickId == R.id.btn_clean) {
            LoadingHelper.showLoading();
            Utils.runOnUiThreadDelayed(new Runnable() {
                @Override
                public void run() {
                    if (CleanUtils.cleanInternalCache()) {
                        ToastUtils.showShort("清理成功");
                    }
                    String cacheSize = FileUtils.getSize(PathUtils.getInternalAppCachePath());
                    btnClean.setRightString(cacheSize);
                    LoadingHelper.hideLoading();
                }
            }, 2000);


        }
    }
}
