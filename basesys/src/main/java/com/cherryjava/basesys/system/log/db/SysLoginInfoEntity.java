package com.cherryjava.basesys.system.log.db;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author hjamm
 * @since 2022-10-02
 */
@TableName("sys_login_info")
public class SysLoginInfoEntity extends Model<SysLoginInfoEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 访问ID
     **/
    @TableId(value = "info_id", type = IdType.AUTO)
    private Long infoId;

    /**
     * 用户账号
     **/
    @TableField("username")
    private String username;

    /**
     * 登录IP地址
     **/
    @TableField("ip_address")
    private String ipAddress;

    /**
     * 登录地点
     **/
    @TableField("login_location")
    private String loginLocation;

    /**
     * 浏览器类型
     **/
    @TableField("browser")
    private String browser;

    /**
     * 操作系统
     **/
    @TableField("operation_system")
    private String operationSystem;

    /**
     * 登录状态（1成功 0失败）
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 提示消息
     **/
    @TableField("msg")
    private String msg;

    /**
     * 访问时间
     **/
    @TableField("login_time")
    private Date loginTime;

    /**
     * 逻辑删除
     **/
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public Serializable pkVal() {
        return this.infoId;
    }

}
