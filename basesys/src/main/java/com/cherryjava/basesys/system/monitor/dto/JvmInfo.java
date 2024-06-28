package com.cherryjava.basesys.system.monitor.dto;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.cherryjava.common.constant.Constants;

import java.lang.management.ManagementFactory;

/**
 * JVM相关信息
 *
 * @author ruoyi
 */
public class JvmInfo {

    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    public double getTotal() {
        return NumberUtil.div(total, Constants.MB, 2);
    }

    public double getMax() {
        return NumberUtil.div(max, Constants.MB, 2);
    }

    public double getFree() {
        return NumberUtil.div(free, Constants.MB, 2);
    }

    public double getUsed() {
        return NumberUtil.div(total - free, Constants.MB, 2);
    }

    public double getUsage() {
        return NumberUtil.div((total - free) * 100, total, 2);
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        return DateUtil.format(DateUtil.date(ManagementFactory.getRuntimeMXBean().getStartTime()),
                DatePattern.NORM_DATETIME_PATTERN);
    }

    /**
     * JDK运行时间
     */
    public String getRunTime() {
        return DateUtil.formatBetween(DateUtil.date(ManagementFactory.getRuntimeMXBean().getStartTime()),
                DateUtil.date());
    }

    /**
     * 运行参数
     */
    public String getInputArgs() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments().toString();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setFree(double free) {
        this.free = free;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
