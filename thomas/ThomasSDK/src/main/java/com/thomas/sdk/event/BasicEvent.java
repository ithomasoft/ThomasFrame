package com.thomas.sdk.event;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/16
 * @updatelog
 * @since
 */
public class BasicEvent {
    private String tag = getClass().getName();

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
