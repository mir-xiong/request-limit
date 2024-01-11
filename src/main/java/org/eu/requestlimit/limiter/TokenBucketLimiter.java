package org.eu.requestlimit.limiter;

import org.eu.requestlimit.config.RequestLimitConfig;

/**
 * @author xiongmin
 * @since create by 2024/1/11 14:59
 */
public class TokenBucketLimiter implements LimiterManager {

    private static final int DEFAULT_INITIALIZED_TOKENS = 10;
    private static final int SIGNAL_REQUEST = 1;

    private int limitQps;

    private int maxTokens;

    private int currentTokens;

    private long lastRequestTimeStamp;

    public TokenBucketLimiter(RequestLimitConfig requestLimitConfig) {
        this.limitQps = requestLimitConfig.getTokenBucketConfig().getLimitQps();
        this.maxTokens = requestLimitConfig.getTokenBucketConfig().getMaxTokens();
        this.currentTokens = Math.max(requestLimitConfig.getTokenBucketConfig().getInitializedTokens(), DEFAULT_INITIALIZED_TOKENS);
        this.lastRequestTimeStamp = System.currentTimeMillis();
    }

    /**
     * 获取令牌
     * @return boolean
     */
    @Override
    public synchronized boolean tryAcquire() {
        long nowTimeStamp = System.currentTimeMillis();
        increaseTokens(nowTimeStamp);
        if (currentTokens < SIGNAL_REQUEST) {
            return false;
        }
        currentTokens -= SIGNAL_REQUEST;
        return true;
    }


    /**
     * 惰性增加令牌数
     *
     * @param nowTimeStamp
     */
    private void increaseTokens(long nowTimeStamp) {

        // 当前时间小于上次请求执行的时间，也就意味着在该请求期间，令牌均已生成
        if (nowTimeStamp <= lastRequestTimeStamp) {
            return;
        }

        // 当前时间晚于下次请求可以执行的时间，也就意味着会有多余的令牌生成

        // 算一下晚了多少毫秒
        long interval = nowTimeStamp - lastRequestTimeStamp;

        // 用时间间隔 * qps 得出这段间隔能生成多少令牌
        int increaseTokens = (int) ((limitQps * interval) / 1000);
        currentTokens = Math.min(increaseTokens + currentTokens, maxTokens);

        // 因为令牌数已经刷新了，所以时间也要改一下
        lastRequestTimeStamp = nowTimeStamp;
    }
}
