package com.cherryjava.admin.service.permission.model.checker;

import cn.hutool.core.collection.CollUtil;
import com.cherryjava.admin.service.permission.model.AbstractDataPermissionChecker;
import com.cherryjava.admin.service.permission.model.DataCondition;
import com.cherryjava.basesys.system.dept.db.SysDeptService;
import com.cherryjava.framework.user.web.SystemLoginUser;

import java.util.Set;

/**
 * 数据权限测试接口
 *
 * @author hjamm
 */
public class CustomDataPermissionChecker extends AbstractDataPermissionChecker {

    private SysDeptService deptService;


    public CustomDataPermissionChecker() {
    }

    public CustomDataPermissionChecker(SysDeptService deptService) {
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

        if (loginUser.getRoleInfo() == null) {
            return false;
        }

        Set<Long> deptIdSet = loginUser.getRoleInfo().getDeptIdSet();
        Long targetDeptId = condition.getTargetDeptId();

        return condition.getTargetDeptId() != null && CollUtil.safeContains(deptIdSet, targetDeptId);
    }
}
