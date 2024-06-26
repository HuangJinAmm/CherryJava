package com.cherryjava.framework.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * 确保应用退出时能关闭后台线程
 *
 * @author ruoyi
 */
@Component
public class ShutdownHook {
    private final static Logger log = LoggerFactory.getLogger(ShutdownHook.class);

    @PreDestroy
    public void destroy() {
        shutdownAllThreadPool();
    }

    /**
     * 停止异步执行任务
     */
    private void shutdownAllThreadPool() {
        try {
            log.info("close thread pool");
            ThreadPoolManager.shutdown();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
