package com.thomas.sdk.helper;

import com.thomas.core.utils.ActivityUtils;
import com.thomas.res.dialog.LoadingDialog;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public class LoadingHelper {
    private static LoadingDialog loadingDialog;

    //显示加载框
    public static void showLoading() {
        loadingDialog = new LoadingDialog(ActivityUtils.getTopActivity());
        loadingDialog.showPopupWindow();
    }

    //取消显示加载框
    public static void hideLoading() {
        if (checkLoadingShow()) {
            loadingDialog.dismiss();
        }

    }

    public static boolean checkLoadingShow() {
        return loadingDialog != null && loadingDialog.isShowing();
    }
}
