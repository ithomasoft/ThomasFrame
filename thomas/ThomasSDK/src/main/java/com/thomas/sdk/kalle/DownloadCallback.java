package com.thomas.sdk.kalle;

import com.thomas.core.utils.StringUtils;
import com.thomas.sdk.R;
import com.yanzhenjie.kalle.download.SimpleCallback;
import com.yanzhenjie.kalle.exception.ConnectTimeoutError;
import com.yanzhenjie.kalle.exception.HostError;
import com.yanzhenjie.kalle.exception.NetworkError;
import com.yanzhenjie.kalle.exception.ReadTimeoutError;
import com.yanzhenjie.kalle.exception.URLError;
import com.yanzhenjie.kalle.exception.WriteException;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/22
 * @updatelog
 * @since
 */
public abstract class DownloadCallback extends SimpleCallback {
    @Override
    public final void onException(Exception e) {
        String message;
        if (e instanceof NetworkError) {
            message = StringUtils.getString(R.string.http_exception_network);
        } else if (e instanceof URLError) {
            message = StringUtils.getString(R.string.http_exception_url);
        } else if (e instanceof HostError) {
            message = StringUtils.getString(R.string.http_exception_host);
        } else if (e instanceof ConnectTimeoutError) {
            message = StringUtils.getString(R.string.http_exception_connect_timeout);
        } else if (e instanceof WriteException) {
            message = StringUtils.getString(R.string.http_exception_write);
        } else if (e instanceof ReadTimeoutError) {
            message = StringUtils.getString(R.string.http_exception_read_timeout);
        } else {
            message = StringUtils.getString(R.string.http_exception_unknow_error);
        }
        onException(message);
    }

    /**
     * Error message.
     */
    public abstract void onException(String message);
}
