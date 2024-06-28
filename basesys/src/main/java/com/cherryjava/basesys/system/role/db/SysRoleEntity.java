package com.cherryjava.basesys.system.role.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cherryjava.common.core.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author hjamm
 * @since 2022-10-02
 */
@TableName("sys_role")
public class SysRoleEntity extends BaseEntity<SysRoleEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     **/
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     **/
    @TableField("role_name")
    private String roleName;

    /**
     * 角色权限字符串
     **/
    @TableField("role_key")
    private String roleKey;

    /**
     * 显示顺序
     **/
    @TableField("role_sort")
    private Integer roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）
     */
    @TableField("data_scope")
    private Integer dataScope;

    /**
     * 角色所拥有的部门数据权限
     **/
    @TableField("dept_id_set")
    private String deptIdSet;

    /**
     * 角色状态（1正常 0停用）
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 备注
     **/
    @TableField("remark")
    private String remark;

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

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getRoleSort() {
        return roleSort;
    }

    public void setRoleSort(Integer roleSort) {
        this.roleSort = roleSort;
    }

    public Integer getDataScope() {
        return dataScope;
    }

    public void setDataScope(Integer dataScope) {
        this.dataScope = dataScope;
    }

    public String getDeptIdSet() {
        return deptIdSet;
    }

    public void setDeptIdSet(String deptIdSet) {
        this.deptIdSet = deptIdSet;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Serializable pkVal() {
        return this.roleId;
    }

}
