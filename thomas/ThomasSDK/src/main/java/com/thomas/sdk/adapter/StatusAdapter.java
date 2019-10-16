package com.thomas.sdk.adapter;

import android.view.View;

import com.thomas.res.widget.ThomasStatusView;
import com.thomas.sdk.helper.StatusHelper;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public class StatusAdapter implements StatusHelper.Adapter {
    @Override
    public View getView(StatusHelper.Holder holder, View convertView, int status) {
        ThomasStatusView statusView = null;

        if (convertView != null && convertView instanceof ThomasStatusView) {
            statusView = (ThomasStatusView) convertView;
        }
        if (statusView == null) {
            statusView = new ThomasStatusView(holder.getContext(), holder.getData(), holder.getRetryTask());
        }
        statusView.setData(holder.getData());
        statusView.setStatus(status);
        return statusView;
    }
}
