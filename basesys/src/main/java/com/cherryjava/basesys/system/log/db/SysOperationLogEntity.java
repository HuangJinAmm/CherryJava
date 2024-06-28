package com.cherryjava.basesys.system.log.db;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 操作日志记录
 * </p>
 *
 * @author hjamm
 * @since 2022-10-02
 */
@TableName("sys_operation_log")
public class SysOperationLogEntity extends Model<SysOperationLogEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     **/
    @TableId(value = "operation_id", type = IdType.AUTO)
    private Long operationId;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @TableField("business_type")
    private Integer businessType;

    /**
     * 请求方式
     **/
    @TableField("request_method")
    private Integer requestMethod;

    /**
     * 请求模块
     **/
    @TableField("request_module")
    private String requestModule;

    /**
     * 请求URL
     **/
    @TableField("request_url")
    private String requestUrl;

    /**
     * 调用方法
     **/
    @TableField("called_method")
    private String calledMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @TableField("operator_type")
    private Integer operatorType;

    /**
     * 用户ID
     **/
    @TableField("user_id")
    private Long userId;

    /**
     * 操作人员
     **/
    @TableField("username")
    private String username;

    /**
     * 操作人员ip
     **/
    @TableField("operator_ip")
    private String operatorIp;

    /**
     * 操作地点
     **/
    @TableField("operator_location")
    private String operatorLocation;

    /**
     * 部门ID
     **/
    @TableField("dept_id")
    private Long deptId;

    /**
     * 部门名称
     **/
    @TableField("dept_name")
    private String deptName;

    /**
     * 请求参数
     **/
    @TableField("operation_param")
    private String operationParam;

    /**
     * 返回参数
     **/
    @TableField("operation_result")
    private String operationResult;

    /**
     * 操作状态（1正常 0异常）
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 错误消息
     **/
    @TableField("error_stack")
    private String errorStack;

    /**
     * 操作时间
     **/
    @TableField("operation_time")
    private Date operationTime;

    /**
     * 逻辑删除
     **/
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

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

    public Integer getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(Integer requestMethod) {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public Serializable pkVal() {
        return this.operationId;
    }

}
