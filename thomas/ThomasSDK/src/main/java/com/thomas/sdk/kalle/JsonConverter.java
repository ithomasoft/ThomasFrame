package com.thomas.sdk.kalle;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.thomas.core.utils.ActivityUtils;
import com.thomas.core.utils.LogUtils;
import com.thomas.core.utils.StringUtils;
import com.thomas.sdk.R;
import com.thomas.sdk.RouterHub;
import com.thomas.sdk.helper.ARouterHelper;
import com.thomas.sdk.helper.UserHelper;
import com.yanzhenjie.kalle.Response;
import com.yanzhenjie.kalle.simple.Converter;
import com.yanzhenjie.kalle.simple.SimpleResponse;

import java.lang.reflect.Type;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/22
 * @updatelog
 * @since
 */
public class JsonConverter implements Converter {

    public JsonConverter() {
    }

    @Override
    public <S, F> SimpleResponse<S, F> convert(Type succeed, Type failed, Response response, boolean fromCache)
            throws Exception {
        S succeedData = null; // The data when the business successful.
        F failedData = null; // The data when the business failed.
        int code = response.code();
        String serverJson = response.body().string();
        LogUtils.iTag("Kalle", serverJson);
        if (code >= 200 && code < 300) { // Http is successful.
            BaseBean httpEntity;
            try {
                httpEntity = JSONObject.parseObject(serverJson, BaseBean.class);
            } catch (Exception e) {
                httpEntity = new BaseBean();
                httpEntity.setErrorCode(-1);
                httpEntity.setErrorMsg(StringUtils.getString(R.string.http_server_data_format_error));
                httpEntity.setData("");
            }

            if (httpEntity.getErrorCode() == 0) {
                try {
                    succeedData = JSONObject.parseObject(httpEntity.getData(), succeed);
                } catch (Exception e) {
                    failedData = (F) StringUtils.getString(R.string.http_server_data_format_error);
                }
            } else {
                //token失效，需要重新登录
                if (httpEntity.getErrorCode() == (-1001)) {
                    UserHelper.cleanUserInfo();
                    ARouterHelper.startActivity(RouterHub.ROUTER_LOGIN);
                }
                // The server failed to read the wrong information.
                failedData = (F) httpEntity.getErrorMsg();

            }

        } else if (code >= 400 && code < 500) {
            failedData = (F) StringUtils.getString(R.string.http_unknow_error);
        } else if (code >= 500) {
            failedData = (F) StringUtils.getString(R.string.http_server_error);
        }

        return SimpleResponse.<S, F>newBuilder().code(response.code())
                .headers(response.headers())
                .fromCache(fromCache)
                .succeed(succeedData)
                .failed(failedData)
                .build();
    }
}
