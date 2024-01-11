package org.eu.requestlimit.model;

/**
 * @author xiongmin
 * @since create by 2024/1/11 10:55
 */
public enum RequestLimitStrategy {
    FIXED_WINDOW,
    ROLLING_WINDOW,
    TOKEN_BUCKET,
    LEAKY_BUCKET,
    ;
}
