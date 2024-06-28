package com.cherryjava.admin.controller.system;

import com.cherryjava.admin.accessLog.AccessLog;
import com.cherryjava.basesys.system.role.RoleApplicationService;
import com.cherryjava.basesys.system.role.command.AddRoleCommand;
import com.cherryjava.basesys.system.role.command.UpdateDataScopeCommand;
import com.cherryjava.basesys.system.role.command.UpdateRoleCommand;
import com.cherryjava.basesys.system.role.command.UpdateStatusCommand;
import com.cherryjava.basesys.system.role.dto.RoleDTO;
import com.cherryjava.basesys.system.role.query.AllocatedRoleQuery;
import com.cherryjava.basesys.system.role.query.RoleQuery;
import com.cherryjava.basesys.system.role.query.UnallocatedRoleQuery;
import com.cherryjava.basesys.system.user.dto.UserDTO;
import com.cherryjava.common.core.base.BaseController;
import com.cherryjava.common.core.dto.ResponseDTO;
import com.cherryjava.common.core.page.PageDTO;
import com.cherryjava.common.enums.common.BusinessTypeEnum;
import com.cherryjava.common.utils.poi.CustomExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 角色信息
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/system/role")
@Validated
public class SysRoleController extends BaseController {

    private final RoleApplicationService roleApplicationService;

    public SysRoleController(RoleApplicationService roleApplicationService) {
        this.roleApplicationService = roleApplicationService;
    }

    @PreAuthorize("@permission.has('system:role:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<RoleDTO>> list(RoleQuery query) {
        PageDTO<RoleDTO> pageDTO = roleApplicationService.getRoleList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('system:role:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, RoleQuery query) {
        PageDTO<RoleDTO> pageDTO = roleApplicationService.getRoleList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), RoleDTO.class, response);
    }

    /**
     * 根据角色编号获取详细信息
     */
    @PreAuthorize("@permission.has('system:role:query')")
    @GetMapping(value = "/{roleId}")
    public ResponseDTO<RoleDTO> getInfo(@PathVariable @NotNull Long roleId) {
        RoleDTO roleInfo = roleApplicationService.getRoleInfo(roleId);
        return ResponseDTO.ok(roleInfo);
    }

    /**
     * 新增角色
     */
    @PreAuthorize("@permission.has('system:role:add')")
    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody AddRoleCommand addCommand) {
        roleApplicationService.addRole(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 移除角色
     */
    @PreAuthorize("@permission.has('system:role:remove')")
    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping(value = "/{roleId}")
    public ResponseDTO<Void> remove(@PathVariable("roleId") List<Long> roleIds) {
        roleApplicationService.deleteRoleByBulk(roleIds);
        return ResponseDTO.ok();
    }

    /**
     * 修改保存角色
     */
    @PreAuthorize("@permission.has('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<Void> edit(@Validated @RequestBody UpdateRoleCommand updateCommand) {
        roleApplicationService.updateRole(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改保存数据权限
     */
    @PreAuthorize("@permission.has('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{roleId}/dataScope")
    public ResponseDTO<Void> dataScope(@PathVariable("roleId") Long roleId,
                                       @RequestBody UpdateDataScopeCommand command) {
        command.setRoleId(roleId);

        roleApplicationService.updateDataScope(command);
        return ResponseDTO.ok();
    }

    /**
     * 角色状态修改
     */
    @PreAuthorize("@permission.has('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{roleId}/status")
    public ResponseDTO<Void> changeStatus(@PathVariable("roleId") Long roleId,
                                          @RequestBody UpdateStatusCommand command) {
        command.setRoleId(roleId);

        roleApplicationService.updateStatus(command);
        return ResponseDTO.ok();
    }


    /**
     * 查询已分配用户角色列表
     */
    @PreAuthorize("@permission.has('system:role:list')")
    @GetMapping("/{roleId}/allocated/list")
    public ResponseDTO<PageDTO<UserDTO>> allocatedUserList(@PathVariable("roleId") Long roleId,
                                                           AllocatedRoleQuery query) {
        query.setRoleId(roleId);
        PageDTO<UserDTO> page = roleApplicationService.getAllocatedUserList(query);
        return ResponseDTO.ok(page);
    }

    /**
     * 查询未分配用户角色列表
     */
    @PreAuthorize("@permission.has('system:role:list')")
    @GetMapping("/{roleId}/unallocated/list")
    public ResponseDTO<PageDTO<UserDTO>> unallocatedUserList(@PathVariable("roleId") Long roleId,
                                                             UnallocatedRoleQuery query) {
        query.setRoleId(roleId);
        PageDTO<UserDTO> page = roleApplicationService.getUnallocatedUserList(query);
        return ResponseDTO.ok(page);
    }


    /**
     * 批量取消授权用户
     */
    @PreAuthorize("@permission.has('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.GRANT)
    @DeleteMapping("/users/{userIds}/grant/bulk")
    public ResponseDTO<Void> deleteRoleOfUserByBulk(@PathVariable("userIds") List<Long> userIds) {
        roleApplicationService.deleteRoleOfUserByBulk(userIds);
        return ResponseDTO.ok();
    }

    /**
     * 批量选择用户授权
     */
    @PreAuthorize("@permission.has('system:role:edit')")
    @AccessLog(title = "角色管理", businessType = BusinessTypeEnum.GRANT)
    @PostMapping("/{roleId}/users/{userIds}/grant/bulk")
    public ResponseDTO<Void> addRoleForUserByBulk(@PathVariable("roleId") Long roleId,
                                                  @PathVariable("userIds") List<Long> userIds) {
        roleApplicationService.addRoleOfUserByBulk(roleId, userIds);
        return ResponseDTO.ok();
    }

}
