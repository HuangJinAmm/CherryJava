package com.cherryjava.basesys.system.monitor.dto;

import cn.hutool.core.util.NumberUtil;

/**
 * CPU相关信息
 *
 * @author ruoyi
 */
public class CpuInfo {

    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;

    public double getTotal() {
        return NumberUtil.round(total * 100, 2).doubleValue();
    }

    public double getSys() {
        return NumberUtil.div(sys * 100, total, 2);
    }

    public double getUsed() {
        return NumberUtil.div(used * 100, total, 2);
    }

    public double getWait() {
        return NumberUtil.div(wait * 100, total, 2);
    }

    public double getFree() {
        return NumberUtil.div(free * 100, total, 2);
    }

    public int getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(int cpuNum) {
        this.cpuNum = cpuNum;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setSys(double sys) {
        this.sys = sys;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public void setWait(double wait) {
        this.wait = wait;
    }

    public void setFree(double free) {
        this.free = free;
    }
}

