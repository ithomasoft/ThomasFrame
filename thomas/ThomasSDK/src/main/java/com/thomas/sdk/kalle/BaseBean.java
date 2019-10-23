package com.thomas.sdk.kalle;

/**
 * @author Thomas
 * @describe
 * @date 2019/10/22
 * @updatelog
 * @since
 */
public class BaseBean {

    /**
     * data :
     * errorCode : 0
     * errorMsg :
     */

    private String data;
    private int errorCode;
    private String errorMsg;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
