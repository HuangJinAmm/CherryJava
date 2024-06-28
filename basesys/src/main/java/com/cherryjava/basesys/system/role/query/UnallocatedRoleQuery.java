package com.cherryjava.basesys.system.role.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cherryjava.basesys.system.user.db.SysUserEntity;
import com.cherryjava.common.core.page.AbstractPageQuery;

/**
 * @author hjamm
 */
public class UnallocatedRoleQuery extends AbstractPageQuery<SysUserEntity> {

    private Long roleId;
    private String username;
    private String phoneNumber;

    @Override
    public QueryWrapper<SysUserEntity> addQueryCondition() {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotEmpty(username), "u.username", username)
                .like(StrUtil.isNotEmpty(phoneNumber), "u.phone_number", phoneNumber)
                .and(o -> o.ne("r.role_id", roleId)
                        .or().isNull("u.role_id")
                        .or().eq("u.role_id", 0));

        return queryWrapper;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
