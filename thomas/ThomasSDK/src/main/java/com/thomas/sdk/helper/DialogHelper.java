package com.thomas.sdk.helper;

import android.content.Context;

import com.thomas.core.utils.ActivityUtils;
import com.thomas.res.dialog.NormalDialog;
import com.thomas.res.dialog.TipsDialog;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class DialogHelper {
    public static void showTipsCenter(String content) {
        TipsDialog.Builder builder = new TipsDialog.Builder(ActivityUtils.getTopActivity());
        builder.setContent(content).setSure("").build().showPopupWindow();
    }

    public static void showTipsCenter(String content, TipsDialog.OnSureClickListener onSureClickListener) {
        TipsDialog.Builder builder = new TipsDialog.Builder(ActivityUtils.getTopActivity());
        TipsDialog dialog = builder.setContent(content).setSure("").build();
        dialog.setOnSureClickListener(onSureClickListener);
        dialog.showPopupWindow();
    }


    public static void showTipsCenter(Context context, String content, TipsDialog.OnSureClickListener onSureClickListener) {
        TipsDialog.Builder builder = new TipsDialog.Builder(context);
        TipsDialog dialog = builder.setContent(content).setSure("").build();
        dialog.setOnSureClickListener(onSureClickListener);
        dialog.showPopupWindow();
    }

    public static void showTipsCenter(String content, String okStr) {
        TipsDialog.Builder builder = new TipsDialog.Builder(ActivityUtils.getTopActivity());
        builder.setContent(content).setSure(okStr).build().showPopupWindow();
    }

    public static void showTipsCenter(String content, String okStr, TipsDialog.OnSureClickListener onSureClickListener) {
        TipsDialog.Builder builder = new TipsDialog.Builder(ActivityUtils.getTopActivity());
        TipsDialog dialog = builder.setContent(content).setSure(okStr).build();
        dialog.setOnSureClickListener(onSureClickListener);
        dialog.showPopupWindow();
    }

    public static void showTipsCenter(Context context, String content, String okStr, TipsDialog.OnSureClickListener onSureClickListener) {
        TipsDialog.Builder builder = new TipsDialog.Builder(context);
        TipsDialog dialog = builder.setContent(content).setSure(okStr).build();
        dialog.setOnSureClickListener(onSureClickListener);
        dialog.showPopupWindow();
    }

    public static void showDialogCenter(String title, String content, NormalDialog.OnDialogListener onDialogListener) {
        showDialogCenter(title, content, "", "", onDialogListener);
    }

    public static void showDialogCenter(String title, String content, String cancelStr, String okStr, NormalDialog.OnDialogListener onDialogListener) {
        NormalDialog.Builder builder = new NormalDialog.Builder(ActivityUtils.getTopActivity());
        NormalDialog dialog = builder.setTitle(title).setContent(content).setCancel(cancelStr).setSure(okStr).build();
        dialog.setOnDialogListener(onDialogListener);
        dialog.showPopupWindow();
    }

}
