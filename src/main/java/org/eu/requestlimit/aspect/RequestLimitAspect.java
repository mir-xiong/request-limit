package org.eu.requestlimit.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.eu.requestlimit.exception.ApiException;
import org.eu.requestlimit.limiter.LimiterManager;

/**
 * @author xiongmin
 * @since create by 2024/1/11 10:02
 */
@Aspect
@Slf4j
public class RequestLimitAspect {

    private LimiterManager limiterManager;

    public RequestLimitAspect(LimiterManager limiterManager) {
        this.limiterManager = limiterManager;
    }

    @Pointcut("@annotation(org.eu.requestlimit.annotation.RequestLimit)")
    public void reqLimitAspect() {
        // 扫描所有@RequestLimit注解
    }


    @Before("reqLimitAspect()")
    public void doBefore() {
        if (!limiterManager.tryAcquire()) {
            throw new ApiException("不好意思，您被限流了");
        }
    }


}
