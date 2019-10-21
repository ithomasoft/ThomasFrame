package com.thomas.andfun.home.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;

import com.thomas.andfun.home.R;
import com.thomas.andfun.home.fragment.contract.MineContract;
import com.thomas.andfun.home.fragment.presenter.MinePresenter;
import com.thomas.core.utils.BarUtils;
import com.thomas.core.utils.ImageUtils;
import com.thomas.core.utils.LogUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.sdk.helper.ImageHelper;
import com.thomas.sdk.ui.LazyThomasMvpFragment;
import com.thomas.sdk.ui.ThomasMvpFragment;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/17
 * @updatelog
 * @since
 */
public class MineFragment extends ThomasMvpFragment<MinePresenter> implements MineContract.View {

    private AppCompatImageView bgMine, ivHead;

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
        BarUtils.setStatusBarLightMode(mActivity, false);
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        bgMine = findViewById(R.id.bg_mine);
        ivHead = findViewById(R.id.iv_head);
        ImageHelper.showSimpleWithBlur(bgMine, R.drawable.bg_mine);
        applyThomasClickListener(findViewById(R.id.tv_nick_name), findViewById(R.id.btn_collection), findViewById(R.id.btn_share)
                , findViewById(R.id.btn_scan), findViewById(R.id.btn_todo)
                , findViewById(R.id.btn_setting), findViewById(R.id.btn_about));
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onThomasClick(@NonNull View view) {
        int clickId = view.getId();

        if (clickId == R.id.tv_nick_name) {
            ToastUtils.showLong("登录");
        }

        if (clickId == R.id.btn_collection) {
            ToastUtils.showLong("我的收藏");
        }
        if (clickId == R.id.btn_share) {
            ToastUtils.showLong("我的分享");
        }
        if (clickId == R.id.btn_scan) {
            ToastUtils.showLong("超级扫码");
        }
        if (clickId == R.id.btn_todo) {
            ToastUtils.showLong("待办清单");
        }
        if (clickId == R.id.btn_setting) {
            ToastUtils.showLong("设置中心");
        }
        if (clickId == R.id.btn_about) {
            ToastUtils.showLong("关于我们");
        }
    }

    @Override
    public void onFailed(String failed) {

    }
}
