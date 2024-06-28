package com.cherryjava.admin.service.permission.model;

import com.cherryjava.basesys.system.dept.db.SysDeptService;
import com.cherryjava.framework.user.web.SystemLoginUser;

/**
 * 数据权限测试接口
 *
 * @author hjamm
 */
public abstract class AbstractDataPermissionChecker {

    private SysDeptService deptService;

    public SysDeptService getDeptService() {
        return deptService;
    }

    public void setDeptService(SysDeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * 检测当前用户对于 给定条件的数据 是否有权限
     *
     * @param loginUser 登录用户
     * @param condition 条件
     * @return 校验结果
     */
    public abstract boolean check(SystemLoginUser loginUser, DataCondition condition);

}
