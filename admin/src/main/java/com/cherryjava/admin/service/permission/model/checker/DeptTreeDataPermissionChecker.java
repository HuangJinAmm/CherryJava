package com.cherryjava.admin.service.permission.model.checker;

import com.cherryjava.admin.service.permission.model.AbstractDataPermissionChecker;
import com.cherryjava.admin.service.permission.model.DataCondition;
import com.cherryjava.basesys.system.dept.db.SysDeptService;
import com.cherryjava.framework.user.web.SystemLoginUser;

import java.util.Objects;

/**
 * 数据权限测试接口
 *
 * @author hjamm
 */
public class DeptTreeDataPermissionChecker extends AbstractDataPermissionChecker {

    private SysDeptService deptService;

    public DeptTreeDataPermissionChecker() {
    }

    public DeptTreeDataPermissionChecker(SysDeptService deptService) {
        this.deptService = deptService;
    }

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
        if (condition == null || loginUser == null) {
            return false;
        }

        if (loginUser.getDeptId() == null || condition.getTargetDeptId() == null) {
            return false;
        }

        Long currentDeptId = loginUser.getDeptId();
        Long targetDeptId = condition.getTargetDeptId();

        boolean isContainsTargetDept = deptService.isChildOfTheDept(loginUser.getDeptId(), targetDeptId);
        boolean isSameDept = Objects.equals(currentDeptId, targetDeptId);

        return isContainsTargetDept || isSameDept;
    }

}
