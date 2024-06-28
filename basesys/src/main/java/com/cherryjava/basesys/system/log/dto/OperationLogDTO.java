package com.cherryjava.basesys.system.log.dto;

import cn.hutool.core.bean.BeanUtil;
import com.cherryjava.basesys.system.log.db.SysOperationLogEntity;
import com.cherryjava.common.annotation.ExcelColumn;
import com.cherryjava.common.annotation.ExcelSheet;
import com.cherryjava.common.enums.BasicEnumUtil;
import com.cherryjava.common.enums.common.BusinessTypeEnum;
import com.cherryjava.common.enums.common.OperationStatusEnum;
import com.cherryjava.common.enums.common.OperatorTypeEnum;
import com.cherryjava.common.enums.common.RequestMethodEnum;

import java.util.Date;

/**
 * @author hjamm
 */
@ExcelSheet(name = "操作日志")
public class OperationLogDTO {

    public OperationLogDTO(SysOperationLogEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
            this.requestMethod = BasicEnumUtil.getDescriptionByValue(RequestMethodEnum.class,
                    entity.getRequestMethod());
            this.statusStr = BasicEnumUtil.getDescriptionByValue(OperationStatusEnum.class, entity.getStatus());
            businessTypeStr = BasicEnumUtil.getDescriptionByValue(BusinessTypeEnum.class, entity.getBusinessType());
            operatorTypeStr = BasicEnumUtil.getDescriptionByValue(OperatorTypeEnum.class, entity.getOperatorType());
        }


    }

    @ExcelColumn(name = "ID")
    private Long operationId;

    private Integer businessType;

    @ExcelColumn(name = "操作类型")
    private String businessTypeStr;

    @ExcelColumn(name = "操作类型")
    private String requestMethod;

    @ExcelColumn(name = "操作类型")
    private String requestModule;

    @ExcelColumn(name = "操作类型")
    private String requestUrl;

    @ExcelColumn(name = "操作类型")
    private String calledMethod;

    private Integer operatorType;

    @ExcelColumn(name = "操作人类型")
    private String operatorTypeStr;

    @ExcelColumn(name = "用户ID")
    private Long userId;

    @ExcelColumn(name = "用户名")
    private String username;

    @ExcelColumn(name = "ip地址")
    private String operatorIp;

    @ExcelColumn(name = "ip地点")
    private String operatorLocation;

    @ExcelColumn(name = "部门ID")
    private Long deptId;

    @ExcelColumn(name = "部门")
    private String deptName;

    @ExcelColumn(name = "操作参数")
    private String operationParam;

    @ExcelColumn(name = "操作结果")
    private String operationResult;

    private Integer status;

    @ExcelColumn(name = "状态")
    private String statusStr;

    @ExcelColumn(name = "错误堆栈")
    private String errorStack;

    @ExcelColumn(name = "操作时间")
    private Date operationTime;

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public String getBusinessTypeStr() {
        return businessTypeStr;
    }

    public void setBusinessTypeStr(String businessTypeStr) {
        this.businessTypeStr = businessTypeStr;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestModule() {
        return requestModule;
    }

    public void setRequestModule(String requestModule) {
        this.requestModule = requestModule;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getCalledMethod() {
        return calledMethod;
    }

    public void setCalledMethod(String calledMethod) {
        this.calledMethod = calledMethod;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public String getOperatorTypeStr() {
        return operatorTypeStr;
    }

    public void setOperatorTypeStr(String operatorTypeStr) {
        this.operatorTypeStr = operatorTypeStr;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperatorIp() {
        return operatorIp;
    }

    public void setOperatorIp(String operatorIp) {
        this.operatorIp = operatorIp;
    }

    public String getOperatorLocation() {
        return operatorLocation;
    }

    public void setOperatorLocation(String operatorLocation) {
        this.operatorLocation = operatorLocation;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getOperationParam() {
        return operationParam;
    }

    public void setOperationParam(String operationParam) {
        this.operationParam = operationParam;
    }

    public String getOperationResult() {
        return operationResult;
    }

    public void setOperationResult(String operationResult) {
        this.operationResult = operationResult;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getErrorStack() {
        return errorStack;
    }

    public void setErrorStack(String errorStack) {
        this.errorStack = errorStack;
    }

    public Date getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(Date operationTime) {
        this.operationTime = operationTime;
    }
}
