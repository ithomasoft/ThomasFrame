package com.thomas.sdk.helper;

import android.app.Activity;

import com.thomas.core.utils.PathUtils;
import com.thomas.core.utils.Utils;
import com.thomas.sdk.BuildConfig;
import com.thomas.sdk.kalle.BaseThomasCallback;
import com.thomas.sdk.kalle.DownloadCallback;
import com.thomas.sdk.kalle.JsonConverter;
import com.thomas.sdk.kalle.ThomasInterceptor;
import com.yanzhenjie.kalle.Kalle;
import com.yanzhenjie.kalle.KalleConfig;
import com.yanzhenjie.kalle.OkHttpConnectFactory;
import com.yanzhenjie.kalle.Params;
import com.yanzhenjie.kalle.connect.RealTimeNetwork;
import com.yanzhenjie.kalle.cookie.DBCookieStore;
import com.yanzhenjie.kalle.simple.cache.CacheMode;
import com.yanzhenjie.kalle.simple.cache.DiskCacheStore;

import java.util.concurrent.TimeUnit;

import static com.yanzhenjie.kalle.Headers.KEY_CONTENT_TYPE;
import static com.yanzhenjie.kalle.Headers.VALUE_APPLICATION_JSON;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public class HttpHelper {
    public static void init() {
        Kalle.setConfig(KalleConfig.newBuilder()
                .connectFactory(OkHttpConnectFactory.newBuilder().build())
                .connectionTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .cookieStore(DBCookieStore.newBuilder(Utils.getApp()).build())
                .cacheStore(DiskCacheStore.newBuilder(PathUtils.getExternalAppCachePath()).build())
                .network(new RealTimeNetwork(Utils.getApp()))
                .setHeader(KEY_CONTENT_TYPE, VALUE_APPLICATION_JSON)
                .addInterceptor(new ThomasInterceptor("Kalle", BuildConfig.DEBUG))
                .converter(new JsonConverter())
                .build());
    }

    public static void post(String url, Params params, BaseThomasCallback thomasCallback) {
        Kalle.post(url).params(params).perform(thomasCallback);
    }

    public static void post(String url, BaseThomasCallback thomasCallback) {
        Kalle.post(url).perform(thomasCallback);
    }

    public static void get(String url, BaseThomasCallback thomasCallback) {
        Kalle.get(url).perform(thomasCallback);
    }


    public static void cancelRequest(Activity activity) {
        Kalle.cancel(activity.getClass());
    }

    public static void download(String url, String filePath, String fileName, DownloadCallback callback) {
        Kalle.Download.get(url).directory(filePath).fileName(fileName).perform(callback);
    }

}
