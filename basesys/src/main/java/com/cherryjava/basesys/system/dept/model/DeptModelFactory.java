package com.cherryjava.basesys.system.dept.model;

import com.cherryjava.basesys.system.dept.command.AddDeptCommand;
import com.cherryjava.basesys.system.dept.db.SysDeptEntity;
import com.cherryjava.basesys.system.dept.db.SysDeptService;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 部门模型工厂
 *
 * @author hjamm
 */
@Component
public class DeptModelFactory {

    private final SysDeptService deptService;

    public DeptModelFactory(SysDeptService deptService) {
        this.deptService = deptService;
    }

    public DeptModel loadById(Long deptId) {
        SysDeptEntity byId = deptService.getById(deptId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, deptId, "部门");
        }
        return new DeptModel(byId, deptService);
    }

    public DeptModel create() {
        return new DeptModel(deptService);
    }

    public DeptModel loadFromAddCommand(AddDeptCommand addCommand, DeptModel model) {
        model.setParentId(addCommand.getParentId());
        model.setDeptName(addCommand.getDeptName());
        model.setOrderNum(addCommand.getOrderNum());
        model.setLeaderName(addCommand.getLeaderName());
        model.setPhone(addCommand.getPhone());
        model.setEmail(addCommand.getEmail());
        return model;
    }


}
