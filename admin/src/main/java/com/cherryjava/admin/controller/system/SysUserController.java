package com.cherryjava.admin.controller.system;

import cn.hutool.core.collection.ListUtil;
import com.cherryjava.admin.accessLog.AccessLog;
import com.cherryjava.basesys.common.command.BulkOperationCommand;
import com.cherryjava.basesys.system.user.UserApplicationService;
import com.cherryjava.basesys.system.user.command.AddUserCommand;
import com.cherryjava.basesys.system.user.command.ChangeStatusCommand;
import com.cherryjava.basesys.system.user.command.ResetPasswordCommand;
import com.cherryjava.basesys.system.user.command.UpdateUserCommand;
import com.cherryjava.basesys.system.user.db.SearchUserDO;
import com.cherryjava.basesys.system.user.dto.UserDTO;
import com.cherryjava.basesys.system.user.dto.UserDetailDTO;
import com.cherryjava.basesys.system.user.query.SearchUserQuery;
import com.cherryjava.common.core.base.BaseController;
import com.cherryjava.common.core.dto.ResponseDTO;
import com.cherryjava.common.core.page.PageDTO;
import com.cherryjava.common.enums.common.BusinessTypeEnum;
import com.cherryjava.common.utils.poi.CustomExcelUtil;
import com.cherryjava.framework.user.AuthenticationUtils;
import com.cherryjava.framework.user.web.SystemLoginUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户信息
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/system/users")
public class SysUserController extends BaseController {

    private final UserApplicationService userApplicationService;

    public SysUserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    /**
     * 获取用户列表
     */
    @PreAuthorize("@permission.has('system:user:list') AND @dataScope.checkDeptId(#query.deptId)")
    @GetMapping
    public ResponseDTO<PageDTO<UserDTO>> userList(SearchUserQuery<SearchUserDO> query) {
        PageDTO<UserDTO> page = userApplicationService.getUserList(query);
        return ResponseDTO.ok(page);
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('system:user:export')")
    @GetMapping("/excel")
    public void exportUserByExcel(HttpServletResponse response, SearchUserQuery<SearchUserDO> query) {
        PageDTO<UserDTO> userList = userApplicationService.getUserList(query);
        CustomExcelUtil.writeToResponse(userList.getRows(), UserDTO.class, response);
    }

    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.IMPORT)
    @PreAuthorize("@permission.has('system:user:import')")
    @PostMapping("/excel")
    public ResponseDTO<Void> importUserByExcel(MultipartFile file) {
        List<AddUserCommand> commands = CustomExcelUtil.readFromRequest(AddUserCommand.class, file);

        for (AddUserCommand command : commands) {
            userApplicationService.addUser(command);
        }
        return ResponseDTO.ok();
    }

    /**
     * 下载批量导入模板
     */
    @GetMapping("/excelTemplate")
    public void downloadExcelTemplate(HttpServletResponse response) {
        CustomExcelUtil.writeToResponse(ListUtil.toList(new AddUserCommand()), AddUserCommand.class, response);
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@permission.has('system:user:query')")
    @GetMapping("/{userId}")
    public ResponseDTO<UserDetailDTO> getUserDetailInfo(@PathVariable(value = "userId", required = false) Long userId) {
        UserDetailDTO userDetailInfo = userApplicationService.getUserDetailInfo(userId);
        return ResponseDTO.ok(userDetailInfo);
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@permission.has('system:user:add') AND @dataScope.checkDeptId(#command.deptId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@Validated @RequestBody AddUserCommand command) {
        userApplicationService.addUser(command);
        return ResponseDTO.ok();
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@permission.has('system:user:edit') AND @dataScope.checkUserId(#command.userId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{userId}")
    public ResponseDTO<Void> edit(@Validated @RequestBody UpdateUserCommand command) {
        userApplicationService.updateUser(command);
        return ResponseDTO.ok();
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@permission.has('system:user:remove') AND @dataScope.checkUserIds(#userIds)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{userIds}")
    public ResponseDTO<Void> remove(@PathVariable List<Long> userIds) {
        BulkOperationCommand<Long> bulkDeleteCommand = new BulkOperationCommand<>(userIds);
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        userApplicationService.deleteUsers(loginUser, bulkDeleteCommand);
        return ResponseDTO.ok();
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@permission.has('system:user:resetPwd') AND @dataScope.checkUserId(#userId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{userId}/password")
    public ResponseDTO<Void> resetPassword(@PathVariable Long userId, @RequestBody ResetPasswordCommand command) {
        command.setUserId(userId);
        userApplicationService.resetUserPassword(command);
        return ResponseDTO.ok();
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@permission.has('system:user:edit') AND @dataScope.checkUserId(#command.userId)")
    @AccessLog(title = "用户管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping("/{userId}/status")
    public ResponseDTO<Void> changeStatus(@PathVariable Long userId, @RequestBody ChangeStatusCommand command) {
        command.setUserId(userId);
        userApplicationService.changeUserStatus(command);
        return ResponseDTO.ok();
    }


}
