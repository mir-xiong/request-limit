package org.eu.requestlimit.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eu.requestlimit.model.ImplMode;
import org.eu.requestlimit.model.RequestLimitStrategy;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author xiongmin
 * @since create by 2024/1/11 11:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "request-limit")
public class RequestLimitConfig {
    private ImplMode mode = ImplMode.ASPECT;
    private RequestLimitStrategy strategy = RequestLimitStrategy.TOKEN_BUCKET;

    private TokenBucketConfig tokenBucketConfig;
}
