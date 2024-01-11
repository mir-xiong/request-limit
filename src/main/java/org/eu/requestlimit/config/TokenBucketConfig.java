package org.eu.requestlimit.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiongmin
 * @since create by 2024/1/11 15:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenBucketConfig {
    private int limitQps;
    private int maxTokens;
    private int initializedTokens;
}
