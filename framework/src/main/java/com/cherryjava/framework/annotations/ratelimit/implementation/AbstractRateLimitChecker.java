package com.cherryjava.framework.annotations.ratelimit.implementation;

import com.cherryjava.framework.annotations.ratelimit.RateLimit;

/**
 * @author hjamm
 */
public abstract class AbstractRateLimitChecker {

    /**
     * 检查是否超出限流
     *
     * @param rateLimiter RateLimit
     */
    public abstract void check(RateLimit rateLimiter);

}
