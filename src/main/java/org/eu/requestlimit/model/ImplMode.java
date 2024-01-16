package org.eu.requestlimit.model;

/**
 * @author xiongmin
 * @since create by 2024/1/11 11:06
 */
public enum ImplMode {
    FILTER,
    INTERCEPTOR,
    ASPECT,
    ;

    public static final ImplMode DEFAULT_MODE = ASPECT;
}
