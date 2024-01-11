package org.eu.requestlimit.config;

import org.eu.requestlimit.annotation.EnableRequestLimit;
import org.eu.requestlimit.aspect.RequestLimitAspect;
import org.eu.requestlimit.limiter.TokenBucketLimiter;
import org.eu.requestlimit.model.ImplMode;
import org.eu.requestlimit.model.RequestLimitStrategy;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author xiongmin
 * @since create by 2024/1/11 17:02
 */
public class RequestLimitConfigurationSelector implements ImportSelector {

    private RequestLimitConfig requestLimitConfig;

    public RequestLimitConfigurationSelector(RequestLimitConfig requestLimitConfig) {
        this.requestLimitConfig = requestLimitConfig;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        final Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableRequestLimit.class.getName(), true);

        List<String> needDIBeans = new ArrayList<>();


        var mode = (ImplMode) annotationAttributes.get("mode");

        /**
         * 当yaml文件配置的模式
         */
        if (!Objects.equals(requestLimitConfig.getMode(), ImplMode.ASPECT) &&
                !Objects.equals(requestLimitConfig.getMode(), mode)) {
            mode = requestLimitConfig.getMode();
        }

        switch (mode) {
            case ASPECT -> needDIBeans.add(RequestLimitAspect.class.getName());
            default -> throw new RuntimeException(String.format("暂不支持[%s]限流模式", mode));
        }

        var strategy = (RequestLimitStrategy) annotationAttributes.get("strategy");
        switch (strategy) {
            case TOKEN_BUCKET -> needDIBeans.add(TokenBucketLimiter.class.getName());
            default -> throw new RuntimeException(String.format("暂不支持[%s]限流策略", strategy));
        }

        var size = needDIBeans.size();
        var beans = new String[size];
        for (int i = 0; i < size; i++) {
            beans[i] = needDIBeans.get(i);
        }

        return beans;
    }
}
