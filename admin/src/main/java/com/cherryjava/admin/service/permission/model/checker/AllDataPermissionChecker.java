package com.cherryjava.admin.service.permission.model.checker;

import com.cherryjava.admin.service.permission.model.AbstractDataPermissionChecker;
import com.cherryjava.admin.service.permission.model.DataCondition;
import com.cherryjava.basesys.system.dept.db.SysDeptService;
import com.cherryjava.framework.user.web.SystemLoginUser;

/**
 * 数据权限测试接口
 *
 * @author hjamm
 */
public class AllDataPermissionChecker extends AbstractDataPermissionChecker {

    private SysDeptService deptService;

    @Override
    public SysDeptService getDeptService() {
        return deptService;
    }

    @Override
    public void setDeptService(SysDeptService deptService) {
        this.deptService = deptService;
    }

    @Override
    public boolean check(SystemLoginUser loginUser, DataCondition condition) {
        return true;
    }
}
