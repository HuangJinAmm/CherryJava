package com.cherryjava.admin.controller.system;

import cn.hutool.core.lang.tree.Tree;
import com.cherryjava.admin.accessLog.AccessLog;
import com.cherryjava.basesys.system.dept.DeptApplicationService;
import com.cherryjava.basesys.system.dept.command.AddDeptCommand;
import com.cherryjava.basesys.system.dept.command.UpdateDeptCommand;
import com.cherryjava.basesys.system.dept.dto.DeptDTO;
import com.cherryjava.basesys.system.dept.query.DeptQuery;
import com.cherryjava.common.core.base.BaseController;
import com.cherryjava.common.core.dto.ResponseDTO;
import com.cherryjava.common.enums.common.BusinessTypeEnum;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 部门信息
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/system")
@Validated
public class SysDeptController extends BaseController {

    private final DeptApplicationService deptApplicationService;

    public SysDeptController(DeptApplicationService deptApplicationService) {
        this.deptApplicationService = deptApplicationService;
    }

    /**
     * 获取部门列表
     */
    @PreAuthorize("@permission.has('system:dept:list')")
    @GetMapping("/depts")
    public ResponseDTO<List<DeptDTO>> list(DeptQuery query) {
        List<DeptDTO> deptList = deptApplicationService.getDeptList(query);
        return ResponseDTO.ok(deptList);
    }

    /**
     * 根据部门编号获取详细信息
     */
    @PreAuthorize("@permission.has('system:dept:query')")
    @GetMapping(value = "/dept/{deptId}")
    public ResponseDTO<DeptDTO> getInfo(@PathVariable Long deptId) {
        DeptDTO dept = deptApplicationService.getDeptInfo(deptId);
        return ResponseDTO.ok(dept);
    }

    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/depts/dropdown")
    public ResponseDTO<List<Tree<Long>>> dropdownList() {
        List<Tree<Long>> deptTree = deptApplicationService.getDeptTree();
        return ResponseDTO.ok(deptTree);
    }

    /**
     * 新增部门
     */
    @PreAuthorize("@permission.has('system:dept:add')")
    @AccessLog(title = "部门管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping("/dept")
    public ResponseDTO<Void> add(@RequestBody AddDeptCommand addCommand) {
        deptApplicationService.addDept(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改部门
     */
    @PreAuthorize("@permission.has('system:dept:edit') AND @dataScope.checkDeptId(#updateCommand.deptId)")
    @AccessLog(title = "部门管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/dept/{deptId}")
    public ResponseDTO<Void> edit(@PathVariable("deptId") Long deptId, @RequestBody UpdateDeptCommand updateCommand) {
        updateCommand.setDeptId(deptId);
        deptApplicationService.updateDept(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除部门
     */
    @PreAuthorize("@permission.has('system:dept:remove') AND @dataScope.checkDeptId(#deptId)")
    @AccessLog(title = "部门管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/dept/{deptId}")
    public ResponseDTO<Void> remove(@PathVariable @NotNull Long deptId) {
        deptApplicationService.removeDept(deptId);
        return ResponseDTO.ok();
    }
}
