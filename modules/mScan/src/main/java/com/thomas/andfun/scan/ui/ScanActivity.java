package com.thomas.andfun.scan.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.thomas.andfun.scan.R;
import com.thomas.andfun.scan.R2;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.ScreenUtils;
import com.thomas.core.utils.SizeUtils;
import com.thomas.core.utils.ToastUtils;
import com.thomas.core.utils.VibrateUtils;
import com.thomas.res.dialog.NormalDialog;
import com.thomas.res.dialog.TipsDialog;
import com.thomas.res.widget.ThomasTitleBar;
import com.thomas.sdk.helper.DialogHelper;
import com.thomas.sdk.ui.ThomasActivity;
import com.thomas.service.RouterHub;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.devilsen.czxing.code.BarcodeFormat;
import me.devilsen.czxing.view.ScanBoxView;
import me.devilsen.czxing.view.ScanListener;
import me.devilsen.czxing.view.ScanView;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/12
 * @updatelog
 * @since
 */
@Route(path = RouterHub.ROUTER_SCAN)
public class ScanActivity extends ThomasActivity {

    @BindView(R2.id.title_bar)
    ThomasTitleBar titleBar;
    @BindView(R2.id.scan_view)
    ScanView scanView;

    @Override
    public boolean isNeedRegister() {
        return false;
    }

    @Override
    public void initData(@NonNull Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_scan;
    }

    @Override
    public void initView(Bundle savedInstanceState, View contentView) {
        titleBar.setListener((view, action, extra) -> {
            if (action == ThomasTitleBar.ACTION_LEFT_BUTTON) {
                ActivityUtils.finishActivity(mActivity);
            }
        });
        ScanBoxView scanBoxView = scanView.getScanBox();
        scanBoxView.setBoxTopOffset(-SizeUtils.dp2px(120));

        scanView.setScanListener(new ScanListener() {
            @Override
            public void onScanSuccess(String result, BarcodeFormat format) {
                VibrateUtils.vibrate(300);
                parseResult(result);
            }

            @Override
            public void onOpenCameraError() {
                DialogHelper.showTipsCenter("相机出现异常，请稍后重试", new TipsDialog.OnSureClickListener() {
                    @Override
                    public void onSure() {
                        ActivityUtils.finishActivity(mActivity);
                    }
                });
            }
        });
    }

    private void parseResult(String result) {
        DialogHelper.showDialogCenter("扫描结果", result, "重新扫码", "复制结果", new NormalDialog.OnDialogListener() {
            @Override
            public void onCancel() {
                scanView.startScan();
            }

            @Override
            public void onSure() {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("OcrText", result);
                clipboardManager.setPrimaryClip(mClipData);
                ToastUtils.showShort("复制成功");
            }
        });

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        scanView.openCamera(); // 打开后置摄像头开始预览，但是并未开始识别
        scanView.startScan();  // 显示扫描框，并开始识别
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanView.stopScan();
        scanView.closeCamera(); // 关闭摄像头预览，并且隐藏扫描框
    }

    @Override
    protected void onDestroy() {
        scanView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }
}
