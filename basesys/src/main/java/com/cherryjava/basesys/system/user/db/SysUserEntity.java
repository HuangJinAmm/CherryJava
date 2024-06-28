package com.cherryjava.basesys.system.user.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cherryjava.common.core.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author hjamm
 * @since 2023-02-27
 */
@TableName("sys_user")
public class SysUserEntity extends BaseEntity<SysUserEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     **/
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 职位id
     **/
    @TableField("post_id")
    private Long postId;

    /**
     * 角色id
     **/
    @TableField("role_id")
    private Long roleId;

    /**
     * 部门ID
     **/
    @TableField("dept_id")
    private Long deptId;

    /**
     * 用户账号
     **/
    @TableField("username")
    private String username;

    /**
     * 用户昵称
     **/
    @TableField("nickname")
    private String nickname;

    /**
     * 用户类型（00系统用户）
     **/
    @TableField("user_type")
    private Integer userType;

    /**
     * 用户邮箱
     **/
    @TableField("email")
    private String email;

    /**
     * 手机号码
     **/
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 用户性别（0男 1女 2未知）
     */
    @TableField("sex")
    private Integer sex;

    /**
     * 头像地址
     **/
    @TableField("avatar")
    private String avatar;

    /**
     * 密码
     **/
    @TableField("`password`")
    private String password;

    /**
     * 帐号状态（1正常 2停用 3冻结）
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 最后登录IP
     **/
    @TableField("login_ip")
    private String loginIp;

    /**
     * 最后登录时间
     **/
    @TableField("login_date")
    private Date loginDate;

    /**
     * 超级管理员标志（1是，0否）
     **/
    @TableField("is_admin")
    private Boolean isAdmin;

    /**
     * 备注
     **/
    @TableField("remark")
    private String remark;


    @Override
    public Serializable pkVal() {
        return this.userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
