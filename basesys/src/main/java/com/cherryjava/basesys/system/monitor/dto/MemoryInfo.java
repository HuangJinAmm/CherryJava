package com.cherryjava.basesys.system.monitor.dto;

import cn.hutool.core.util.NumberUtil;
import com.cherryjava.common.constant.Constants;

/**
 * 內存相关信息
 *
 * @author hjamm
 */
public class MemoryInfo {

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    public double getTotal() {
        return NumberUtil.div(total, Constants.GB, 2);
    }

    public double getUsed() {
        return NumberUtil.div(used, Constants.GB, 2);
    }

    public double getFree() {
        return NumberUtil.div(free, Constants.GB, 2);
    }

    public double getUsage() {
        return NumberUtil.div(used * 100, total, 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public void setFree(double free) {
        this.free = free;
    }
}
