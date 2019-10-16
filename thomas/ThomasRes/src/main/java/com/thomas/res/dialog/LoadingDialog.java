package com.thomas.res.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;

import com.thomas.res.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * @author Thomas
 * @describe 加载等待弹窗
 * @date 2019/10/11
 * @updatelog
 * @since
 */
public class LoadingDialog extends BasePopupWindow {
    public LoadingDialog(Context context) {
        super(context);
        setPopupGravity(Gravity.CENTER);
        setClipChildren(false);
        setOutSideDismiss(false);
        setOutSideTouchable(false);
    }

    @Override
    public View onCreateContentView() {
        return createPopupById(R.layout.thomas_dialog_loading);
    }

    @Override
    protected Animation onCreateShowAnimation() {
        return getDefaultScaleAnimation();
    }

    @Override
    protected Animation onCreateDismissAnimation() {
        return getDefaultScaleAnimation(false);
    }

}
