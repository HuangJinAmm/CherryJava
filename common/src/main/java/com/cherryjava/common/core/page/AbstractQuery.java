package com.cherryjava.common.core.page;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cherryjava.common.utils.time.DatePickUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import java.util.Date;

/**
 * 如果是简单的排序 和 时间范围筛选  可以使用内置的这几个字段
 *
 * @author hjamm
 */
public abstract class AbstractQuery<T> {

    protected String orderColumn;

    protected String orderDirection;

    protected String timeRangeColumn;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endTime;

    private static final String ASC = "ascending";
    private static final String DESC = "descending";

    /**
     * 生成query conditions
     *
     * @return 添加条件后的QueryWrapper
     */
    public QueryWrapper<T> toQueryWrapper() {
        QueryWrapper<T> queryWrapper = addQueryCondition();
        addSortCondition(queryWrapper);
        addTimeCondition(queryWrapper);

        return queryWrapper;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public abstract QueryWrapper<T> addQueryCondition();

    public void addSortCondition(QueryWrapper<T> queryWrapper) {
        if (queryWrapper == null || StrUtil.isEmpty(orderColumn)) {
            return;
        }

        Boolean sortDirection = convertSortDirection();
        if (sortDirection != null) {
            queryWrapper.orderBy(StrUtil.isNotEmpty(orderColumn), sortDirection,
                    StrUtil.toUnderlineCase(orderColumn));
        }
    }

    public void addTimeCondition(QueryWrapper<T> queryWrapper) {
        if (queryWrapper != null
                && StrUtil.isNotEmpty(this.timeRangeColumn)) {
            queryWrapper
                    .ge(beginTime != null, StrUtil.toUnderlineCase(timeRangeColumn),
                            DatePickUtil.getBeginOfTheDay(beginTime))
                    .le(endTime != null, StrUtil.toUnderlineCase(timeRangeColumn), DatePickUtil.getEndOfTheDay(endTime));
        }
    }

    /**
     * 获取前端传来的排序方向  转换成MyBatisPlus所需的排序参数 boolean=isAsc
     *
     * @return 排序顺序， null为无排序
     */
    public Boolean convertSortDirection() {
        Boolean isAsc = null;
        if (StrUtil.isEmpty(this.orderDirection)) {
            return isAsc;
        }

        if (ASC.equals(this.orderDirection)) {
            isAsc = true;
        }
        if (DESC.equals(this.orderDirection)) {
            isAsc = false;
        }

        return isAsc;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public String getTimeRangeColumn() {
        return timeRangeColumn;
    }

    public void setTimeRangeColumn(String timeRangeColumn) {
        this.timeRangeColumn = timeRangeColumn;
    }
}
