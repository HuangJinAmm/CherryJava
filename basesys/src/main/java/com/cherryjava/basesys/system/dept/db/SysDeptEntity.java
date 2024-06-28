package com.cherryjava.basesys.system.dept.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cherryjava.common.core.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author hjamm
 * @since 2022-10-02
 */
@TableName("sys_dept")
public class SysDeptEntity extends BaseEntity<SysDeptEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门id
     **/
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;

    /**
     * 父部门id
     **/
    @TableField("parent_id")
    private Long parentId;

    /**
     * 祖级列表
     **/
    @TableField("ancestors")
    private String ancestors;

    /**
     * 部门名称
     **/
    @TableField("dept_name")
    private String deptName;

    /**
     * 显示顺序
     **/
    @TableField("order_num")
    private Integer orderNum;

    @TableField("leader_id")
    private Long leaderId;

    /**
     * 负责人
     **/
    @TableField("leader_name")
    private String leaderName;

    /**
     * 联系电话
     **/
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     **/
    @TableField("email")
    private String email;

    /**
     * 部门状态（0正常 1停用）
     */
    @TableField("`status`")
    private Integer status;


    @Override
    public Serializable pkVal() {
        return this.deptId;
    }


    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Long leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
