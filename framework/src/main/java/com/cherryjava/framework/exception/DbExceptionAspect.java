package com.cherryjava.framework.exception;

import cn.hutool.core.map.MapUtil;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import org.apache.ibatis.exceptions.PersistenceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.SQLException;


/**
 * @author hjamm
 */
@Aspect
@Component
public class DbExceptionAspect {

    private static final Logger log = LoggerFactory.getLogger(DbExceptionAspect.class);

    @Pointcut("execution(* com.cherryjava..db..*(..))")
    public void dbException() {
    }

    /**
     * 包装成ApiException 再交给globalExceptionHandler处理
     *
     * @param joinPoint joinPoint
     * @return object
     */
    @Around("dbException()")
    public Object aroundDbException(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        try {
            // 将应用层的数据库错误 捕获并进行转换  主要捕获 sql形式的异常
            proceed = joinPoint.proceed();
        } catch (ApiException apiException) {
            throw apiException;
        } catch (Exception sqlException) {
            ApiException wrapException = new ApiException(sqlException, ErrorCode.Internal.DB_INTERNAL_ERROR);
            wrapException.setPayload(MapUtil.of("detail", sqlException.getMessage()));
            throw wrapException;
        }
        return proceed;
    }

    @Pointcut("bean(*ApplicationService)")
    public void applicationDbException() {
    }

    @Around("applicationDbException()")
    public Object aroundApplicationDbException(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        try {
            // 将应用层的数据库错误 捕获并进行转换  主要捕获 jpa形式的  insert save 等模型抛出的错误
            proceed = joinPoint.proceed();
        } catch (ApiException ae) {
            throw ae;
        } catch (SQLException | PersistenceException sqlException) {
            ApiException wrapException = new ApiException(sqlException, ErrorCode.Internal.DB_INTERNAL_ERROR);
            wrapException.setPayload(MapUtil.of("detail", sqlException.getMessage()));
            throw wrapException;
        }

        return proceed;
    }


}
