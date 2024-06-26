package com.cherryjava.framework.annotations.ratelimit;

import com.cherryjava.framework.annotations.ratelimit.implementation.MapRateLimitChecker;
import com.cherryjava.framework.annotations.ratelimit.implementation.RedisRateLimitChecker;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 限流切面处理
 *
 * @author hjamm
 */
@Aspect
@Component
@ConditionalOnExpression("'${agileboot.embedded.redis}' != 'true'")
public class RateLimiterAspect {

    private static final Logger log = LoggerFactory.getLogger(RateLimiterAspect.class);

    private final RedisRateLimitChecker redisRateLimitChecker;

    private final MapRateLimitChecker mapRateLimitChecker;

    public RateLimiterAspect(RedisRateLimitChecker redisRateLimitChecker, MapRateLimitChecker mapRateLimitChecker) {
        this.redisRateLimitChecker = redisRateLimitChecker;
        this.mapRateLimitChecker = mapRateLimitChecker;
    }

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimit rateLimiter) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        log.info("当前限流方法:" + method.toGenericString());

        switch (rateLimiter.cacheType()) {
            case REDIS:
                redisRateLimitChecker.check(rateLimiter);
                break;
            case Map:
                mapRateLimitChecker.check(rateLimiter);
                return;
            default:
                redisRateLimitChecker.check(rateLimiter);
        }

    }

}
