package com.cherryjava.basesys.system.monitor.dto;

import com.cherryjava.basesys.common.cache.CacheCenter;
import com.cherryjava.basesys.system.dept.db.SysDeptEntity;
import com.cherryjava.framework.user.web.SystemLoginUser;

/**
 * 当前在线会话
 *
 * @author ruoyi
 */
public class OnlineUserDTO {

    /**
     * 会话编号
     */
    private String tokenId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 登录IP地址
     */
    private String ipAddress;

    /**
     * 登录地址
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String operationSystem;

    /**
     * 登录时间
     */
    private Long loginTime;


    public OnlineUserDTO(SystemLoginUser user) {
        if (user == null) {
            return;
        }
        this.setTokenId(user.getCachedKey());
        this.tokenId = user.getCachedKey();
        this.username = user.getUsername();
        this.ipAddress = user.getLoginInfo().getIpAddress();
        this.loginLocation = user.getLoginInfo().getLocation();
        this.browser = user.getLoginInfo().getBrowser();
        this.operationSystem = user.getLoginInfo().getOperationSystem();
        this.loginTime = user.getLoginInfo().getLoginTime();

        SysDeptEntity deptEntity = CacheCenter.deptCache.get(user.getDeptId() + "");

        if (deptEntity != null) {
            this.deptName = deptEntity.getDeptName();
        }
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }
}
