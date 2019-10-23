package com.thomas.sdk.helper;

import android.text.TextUtils;

import com.thomas.core.utils.SPUtils;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/22
 * @updatelog
 * @since
 */
public class UserHelper {
    private static String SP_USER = "user";
    private static String SP_USER_EDIT = "user_edit";


    public static void setEditUsername(String username) {
        SPUtils.getInstance(SP_USER_EDIT).put("edit_username", username);
    }

    public static void setEditPassword(String password) {
        SPUtils.getInstance(SP_USER_EDIT).put("edit_password", password);
    }

    public static String getEditUsername() {
        return SPUtils.getInstance(SP_USER_EDIT).getString("edit_username");
    }

    public static String getEditPassword() {
        return SPUtils.getInstance(SP_USER_EDIT).getString("edit_username");
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(getUsername());
    }

    public static void setUserId(int userId) {
        SPUtils.getInstance(SP_USER).put("user_id", userId);
    }

    public static int getUserId() {
        return SPUtils.getInstance(SP_USER).getInt("user_id");
    }

    public static void setUsername(String username) {
        SPUtils.getInstance(SP_USER).put("username", username);
    }

    public static String getUsername() {
        return SPUtils.getInstance(SP_USER).getString("username");
    }

    public static void setNickname(String nickname) {
        SPUtils.getInstance(SP_USER).put("nickname", nickname);
    }

    public static String getNickname() {
        return SPUtils.getInstance(SP_USER).getString("nickname");
    }

    public static void setEmail(String email) {
        SPUtils.getInstance(SP_USER).put("email", email);
    }

    public static String getEmail() {
        return SPUtils.getInstance(SP_USER).getString("email");
    }

    public static void setIcon(String icon) {
        SPUtils.getInstance(SP_USER).put("icon", icon);
    }

    public static String getIcon() {
        return SPUtils.getInstance(SP_USER).getString("icon");
    }

    public static void setType(int type) {
        SPUtils.getInstance(SP_USER).put("type", type);
    }

    public static int getType() {
        return SPUtils.getInstance(SP_USER).getInt("type");
    }


    public static void cleanUserInfo() {
        SPUtils.getInstance(SP_USER).clear();
    }

    public static void cleanEditUserInfo() {
        SPUtils.getInstance(SP_USER_EDIT).clear();
    }


}
