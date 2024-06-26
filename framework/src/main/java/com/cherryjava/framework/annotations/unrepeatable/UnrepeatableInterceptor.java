package com.cherryjava.framework.annotations.unrepeatable;

import cn.hutool.json.JSONUtil;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import com.cherryjava.framework.cache.RedisUtil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 重复提交拦截器 如果涉及前后端加解密的话  也可以通过继承RequestBodyAdvice来实现
 *
 * @author hjamm
 */
@ControllerAdvice(basePackages = "com.cherryjava")
public class UnrepeatableInterceptor extends RequestBodyAdviceAdapter {

    private static final Logger log = LoggerFactory.getLogger(UnrepeatableInterceptor.class);

    private final RedisUtil redisUtil;

    public UnrepeatableInterceptor(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(Unrepeatable.class);
    }

    /**
     * @param body 仅获取有RequestBody注解的参数
     */
    @NotNull
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        // 仅获取有RequestBody注解的参数
        String currentRequest = JSONUtil.toJsonStr(body);

        Unrepeatable resubmitAnno = parameter.getMethodAnnotation(Unrepeatable.class);
        if (resubmitAnno != null) {
            String redisKey = resubmitAnno.checkType().generateResubmitRedisKey(parameter.getMethod());

            log.info("请求重复提交拦截，当前key:{}, 当前参数：{}", redisKey, currentRequest);

            String preRequest = redisUtil.getCacheObject(redisKey);
            if (preRequest != null) {
                boolean isSameRequest = Objects.equals(currentRequest, preRequest);

                if (isSameRequest) {
                    throw new ApiException(ErrorCode.Client.COMMON_REQUEST_RESUBMIT);
                }
            }
            redisUtil.setCacheObject(redisKey, currentRequest, resubmitAnno.interval(), TimeUnit.SECONDS);
        }

        return body;
    }

}
