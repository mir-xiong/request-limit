package org.eu.requestlimit.limiter;

/**
 * @author xiongmin
 * @since create by 2024/1/11 14:57
 */
public interface LimiterManager {

    /**
     * 判断是否允许请求资源
     *
     * @return boolean
     */
    boolean tryAcquire();
}
