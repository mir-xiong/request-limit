package org.eu.requestlimit.exception;

/**
 * @author xiongmin
 * @since create by 2024/1/11 11:33
 */
public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String msg;

    public ApiException(String msg) {
        super(msg);
        this.msg = msg;
    }
}