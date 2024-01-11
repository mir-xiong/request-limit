package org.eu.requestlimit.annotation;

import org.eu.requestlimit.config.RequestLimitConfig;
import org.eu.requestlimit.model.ImplMode;
import org.eu.requestlimit.model.RequestLimitStrategy;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiongmin
 * @since create by 2024/1/10 20:09
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties(RequestLimitConfig.class)
@Documented
public @interface EnableRequestLimit {

    /**
     * 请求限制的实现方式
     * - ImplMode.FILTER：       过滤器实现
     * - ImplMode.INTERCEPTOR：  拦截器实现
     * - ImplMode.ASPECT：       AOP切面实现
     * @return {@link ImplMode}
     */
    ImplMode mode() default ImplMode.ASPECT;

    /**
     * 请求限制策略，默认为令牌桶策略算法
     * @return {@link RequestLimitStrategy}
     */
    RequestLimitStrategy strategy() default RequestLimitStrategy.TOKEN_BUCKET;
}
