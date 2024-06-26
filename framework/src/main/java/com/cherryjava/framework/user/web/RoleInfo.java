package com.cherryjava.framework.user.web;

import org.apache.commons.collections4.SetUtils;

import java.util.Set;

/**
 * @author hjamm
 */
public class RoleInfo {

    public static final RoleInfo EMPTY_ROLE = new RoleInfo();
    public static final long ADMIN_ROLE_ID = -1;
    public static final String ADMIN_ROLE_KEY = "admin";
    public static final String ALL_PERMISSIONS = "*:*:*";

    public static final Set<String> ADMIN_PERMISSIONS = SetUtils.hashSet(ALL_PERMISSIONS);

    public RoleInfo() {
    }

    public RoleInfo(Long roleId, String roleName, DataScopeEnum dataScope, Set<Long> deptIdSet, String roleKey, Set<String> menuPermissions, Set<Long> menuIds) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.dataScope = dataScope;
        this.deptIdSet = deptIdSet;
        this.roleKey = roleKey;
        this.menuPermissions = menuPermissions;
        this.menuIds = menuIds;
    }

    public RoleInfo(Long roleId, String roleKey, DataScopeEnum dataScope, Set<Long> deptIdSet,
                    Set<String> menuPermissions, Set<Long> menuIds) {
        this.roleId = roleId;
        this.roleKey = roleKey;
        this.dataScope = dataScope;
        this.deptIdSet = deptIdSet;
        this.menuPermissions = menuPermissions != null ? menuPermissions : SetUtils.emptySet();
        this.menuIds = menuIds != null ? menuIds : SetUtils.emptySet();
    }


    private Long roleId;
    private String roleName;
    private DataScopeEnum dataScope;
    private Set<Long> deptIdSet;
    private String roleKey;
    private Set<String> menuPermissions;
    private Set<Long> menuIds;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public DataScopeEnum getDataScope() {
        return dataScope;
    }

    public void setDataScope(DataScopeEnum dataScope) {
        this.dataScope = dataScope;
    }

    public Set<Long> getDeptIdSet() {
        return deptIdSet;
    }

    public void setDeptIdSet(Set<Long> deptIdSet) {
        this.deptIdSet = deptIdSet;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Set<String> getMenuPermissions() {
        return menuPermissions;
    }

    public void setMenuPermissions(Set<String> menuPermissions) {
        this.menuPermissions = menuPermissions;
    }

    public Set<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(Set<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
