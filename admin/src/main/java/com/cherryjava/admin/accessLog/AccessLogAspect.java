package com.cherryjava.admin.accessLog;

import com.cherryjava.admin.async.AsyncTaskFactory;
import com.cherryjava.framework.thread.ThreadPoolManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 操作日志记录处理
 *
 * @author hjamm
 */
@Aspect
@Component
public class AccessLogAspect {


    private static final Logger log = LoggerFactory.getLogger(AccessLogAspect.class);

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, AccessLog controllerLog, Object jsonResult) {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, AccessLog controllerLog, Exception e) {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, AccessLog accessLog, final Exception e, Object jsonResult) {
        try {
            OperationLogModel operationLog = new OperationLogModel();
            operationLog.fillOperatorInfo();
            operationLog.fillRequestInfo(joinPoint, accessLog, jsonResult);
            operationLog.fillStatus(e);
            operationLog.fillAccessLogInfo(accessLog);

            // 保存数据库
            ThreadPoolManager.execute(AsyncTaskFactory.recordOperationLog(operationLog));
        } catch (Exception exp) {
            log.error("写入操作日式失败", exp);
        }
    }


}
