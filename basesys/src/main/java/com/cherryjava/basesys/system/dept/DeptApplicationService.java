package com.cherryjava.basesys.system.dept;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.cherryjava.basesys.system.dept.command.AddDeptCommand;
import com.cherryjava.basesys.system.dept.command.UpdateDeptCommand;
import com.cherryjava.basesys.system.dept.db.SysDeptEntity;
import com.cherryjava.basesys.system.dept.db.SysDeptService;
import com.cherryjava.basesys.system.dept.dto.DeptDTO;
import com.cherryjava.basesys.system.dept.model.DeptModel;
import com.cherryjava.basesys.system.dept.model.DeptModelFactory;
import com.cherryjava.basesys.system.dept.query.DeptQuery;
import com.cherryjava.basesys.system.role.db.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门服务
 *
 * @author hjamm
 */
@Service
public class DeptApplicationService {

    private final SysDeptService deptService;

    private final SysRoleService roleService;

    private final DeptModelFactory deptModelFactory;

    public DeptApplicationService(SysDeptService deptService, SysRoleService roleService, DeptModelFactory deptModelFactory) {
        this.deptService = deptService;
        this.roleService = roleService;
        this.deptModelFactory = deptModelFactory;
    }

    public List<DeptDTO> getDeptList(DeptQuery query) {
        List<SysDeptEntity> list = deptService.list(query.toQueryWrapper());
        return list.stream().map(DeptDTO::new).collect(Collectors.toList());
    }

    public DeptDTO getDeptInfo(Long id) {
        SysDeptEntity byId = deptService.getById(id);
        return new DeptDTO(byId);
    }

    public List<Tree<Long>> getDeptTree() {
        List<SysDeptEntity> list = deptService.list();

        return TreeUtil.build(list, 0L, (dept, tree) -> {
            tree.setId(dept.getDeptId());
            tree.setParentId(dept.getParentId());
            tree.putExtra("label", dept.getDeptName());
        });
    }


    public void addDept(AddDeptCommand addCommand) {
        DeptModel deptModel = deptModelFactory.create();
        deptModel.loadAddCommand(addCommand);

        deptModel.checkDeptNameUnique();
        deptModel.generateAncestors();

        deptModel.insert();
    }

    public void updateDept(UpdateDeptCommand updateCommand) {
        DeptModel deptModel = deptModelFactory.loadById(updateCommand.getDeptId());
        deptModel.loadUpdateCommand(updateCommand);

        deptModel.checkDeptNameUnique();
        deptModel.checkParentIdConflict();
        deptModel.checkStatusAllowChange();
        deptModel.generateAncestors();

        deptModel.updateById();
    }

    public void removeDept(Long deptId) {
        DeptModel deptModel = deptModelFactory.loadById(deptId);

        deptModel.checkHasChildDept();
        deptModel.checkDeptAssignedToUsers();

        deptModel.deleteById();
    }


}
