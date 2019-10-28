package com.thomas.sdk.kalle;

import com.thomas.core.utils.StringUtils;
import com.thomas.core.utils.Utils;
import com.thomas.sdk.R;
import com.thomas.sdk.helper.LoadingHelper;
import com.yanzhenjie.kalle.exception.ConnectTimeoutError;
import com.yanzhenjie.kalle.exception.HostError;
import com.yanzhenjie.kalle.exception.NetworkError;
import com.yanzhenjie.kalle.exception.ReadTimeoutError;
import com.yanzhenjie.kalle.exception.URLError;
import com.yanzhenjie.kalle.exception.WriteException;
import com.yanzhenjie.kalle.simple.Callback;
import com.yanzhenjie.kalle.simple.SimpleResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/22
 * @updatelog
 * @since
 */
public abstract class BaseThomasCallback<S> extends Callback<S, String> {

    private long startTime;
    private long endTime;
    private long duration = 2000;

    public BaseThomasCallback() {

    }

    @Override
    public void onStart() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public Type getSucceed() {
        Type superClass = getClass().getGenericSuperclass();
        return ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    @Override
    public Type getFailed() {
        return String.class;
    }

    @Override
    public void onResponse(SimpleResponse<S, String> response) {
            endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            if (time > duration) {
                if (response.isSucceed()) {
                    onSuccess(response.succeed());
                } else {
                    onFailed(response.failed());
                }
                LoadingHelper.hideLoading();
            } else {
                Utils.runOnUiThreadDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSucceed()) {
                            onSuccess(response.succeed());
                        } else {
                            onFailed(response.failed());
                        }
                        LoadingHelper.hideLoading();
                    }
                }, duration - time);
            }

    }

    protected abstract void onSuccess(S succeed);

    protected abstract void onFailed(String failed);


    @Override
    public void onCancel() {

    }

    @Override
    public void onEnd() {

    }

    @Override
    public void onException(Exception e) {
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
        onResponse(SimpleResponse.<S, String>newBuilder().failed(message).build());
    }
}
