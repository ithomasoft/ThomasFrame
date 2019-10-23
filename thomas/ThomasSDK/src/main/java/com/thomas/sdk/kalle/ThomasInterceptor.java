package com.thomas.sdk.kalle;

import com.thomas.core.utils.LogUtils;
import com.yanzhenjie.kalle.Request;
import com.yanzhenjie.kalle.RequestBody;
import com.yanzhenjie.kalle.Response;
import com.yanzhenjie.kalle.StringBody;
import com.yanzhenjie.kalle.UrlBody;
import com.yanzhenjie.kalle.connect.Interceptor;
import com.yanzhenjie.kalle.connect.http.Chain;

import java.io.IOException;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/22
 * @updatelog
 * @since
 */
public class ThomasInterceptor implements Interceptor {
    private final String mTag;
    private final boolean isEnable;

    public ThomasInterceptor(String tag, boolean isEnable) {
        this.mTag = tag;
        this.isEnable = isEnable;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (isEnable) {
            Response response = chain.proceed(request);

            String url = request.url().toString();

            StringBuilder log = new StringBuilder();
            log.append("请求接口：").append(url);
            log.append("\n请求方法：").append(request.method().name());

            if (request.method().allowBody()) {
                RequestBody body = request.body();
                if (body instanceof StringBody || body instanceof UrlBody) {
                    String params = body.toString();
                    log.append("\n请求参数：").append(params);
                }
            }
            LogUtils.iTag(mTag, log.toString());
            return response;
        } else {
            return chain.proceed(request);
        }

    }
}
