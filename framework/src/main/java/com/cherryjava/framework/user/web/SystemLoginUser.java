package com.cherryjava.framework.user.web;

import com.cherryjava.framework.user.base.BaseLoginUser;

/**
 * 登录用户身份权限
 *
 * @author hjamm
 */
public class SystemLoginUser extends BaseLoginUser {

    private static final long serialVersionUID = 1L;

    private boolean isAdmin;

    private Long deptId;

    private RoleInfo roleInfo;

    /**
     * 当超过这个时间 则触发刷新缓存时间
     */
    private Long autoRefreshCacheTime;

    public SystemLoginUser() {
    }

    public SystemLoginUser(Long userId, Boolean isAdmin, String username, String password, RoleInfo roleInfo,
                           Long deptId) {
        this.userId = userId;
        this.isAdmin = isAdmin;
        this.username = username;
        this.password = password;
        this.roleInfo = roleInfo;
        this.deptId = deptId;
    }

    public RoleInfo getRoleInfo() {
        return roleInfo;
    }

    public Long getRoleId() {
        return getRoleInfo().getRoleId();
    }

    public Long getDeptId() {
        return deptId;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setRoleInfo(RoleInfo roleInfo) {
        this.roleInfo = roleInfo;
    }

    public Long getAutoRefreshCacheTime() {
        return autoRefreshCacheTime;
    }

    public void setAutoRefreshCacheTime(Long autoRefreshCacheTime) {
        this.autoRefreshCacheTime = autoRefreshCacheTime;
    }
}
