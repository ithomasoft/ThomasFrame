package com.thomas.andfun.scan.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * @author Thomas
 * @describe
 * @date 2020/1/7
 * @updatelog
 * @since
 */
public class ScanResult extends LitePalSupport {

    @Column(nullable = false, unique = true)
    private String result;
    private String createDate;
    private String type;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
