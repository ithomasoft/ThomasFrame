package com.thomas.sdk.helper;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;

/**
 * @author Thomas
 * @describe
 * @date 2019/12/13
 * @updatelog
 * @since
 */
public class UpdateHelper {

    public static void checkUpdate() {
        Beta.checkUpgrade();
    }
}
