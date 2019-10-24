package com.thomas.sdk.delay;

/**
 * @author Thomas
 * @describe 自定义运行异常
 * @date 2019/10/24
 * @updatelog
 * @since
 */
public class ValidException extends RuntimeException {

    public ValidException() {
    }

    public ValidException(String message) {
        super(message);
    }
}