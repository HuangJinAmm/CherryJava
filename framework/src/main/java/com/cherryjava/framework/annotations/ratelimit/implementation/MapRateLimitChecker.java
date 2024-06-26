package com.cherryjava.framework.annotations.ratelimit.implementation;

import cn.hutool.cache.impl.LRUCache;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import com.cherryjava.framework.annotations.ratelimit.RateLimit;
import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author hjamm
 */
@SuppressWarnings("UnstableApiUsage")
@Component
public class MapRateLimitChecker extends AbstractRateLimitChecker {

    private static final Logger log = LoggerFactory.getLogger(MapRateLimitChecker.class);
    /**
     * 最大仅支持4096个key   超出这个key  限流将可能失效
     */
    private final LRUCache<String, RateLimiter> cache = new LRUCache<>(4096);

    public MapRateLimitChecker() {
    }

    @Override
    public void check(RateLimit rateLimit) {
        String combinedKey = rateLimit.limitType().generateCombinedKey(rateLimit);

        RateLimiter rateLimiter = cache.get(combinedKey,
                () -> RateLimiter.create((double) rateLimit.maxCount() / rateLimit.time())
        );

        if (!rateLimiter.tryAcquire()) {
            throw new ApiException(ErrorCode.Client.COMMON_REQUEST_TOO_OFTEN);
        }

        log.info("限制请求key:{}, combined key:{}", rateLimit.key(), combinedKey);
    }

}
