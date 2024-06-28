package com.cherryjava.basesys.system.role;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cherryjava.basesys.common.cache.CacheCenter;
import com.cherryjava.basesys.system.menu.db.SysMenuService;
import com.cherryjava.basesys.system.role.command.AddRoleCommand;
import com.cherryjava.basesys.system.role.command.UpdateDataScopeCommand;
import com.cherryjava.basesys.system.role.command.UpdateRoleCommand;
import com.cherryjava.basesys.system.role.command.UpdateStatusCommand;
import com.cherryjava.basesys.system.role.db.SysRoleEntity;
import com.cherryjava.basesys.system.role.db.SysRoleService;
import com.cherryjava.basesys.system.role.dto.RoleDTO;
import com.cherryjava.basesys.system.role.model.RoleModel;
import com.cherryjava.basesys.system.role.model.RoleModelFactory;
import com.cherryjava.basesys.system.role.query.AllocatedRoleQuery;
import com.cherryjava.basesys.system.role.query.RoleQuery;
import com.cherryjava.basesys.system.role.query.UnallocatedRoleQuery;
import com.cherryjava.basesys.system.user.db.SysUserEntity;
import com.cherryjava.basesys.system.user.db.SysUserService;
import com.cherryjava.basesys.system.user.dto.UserDTO;
import com.cherryjava.basesys.system.user.model.UserModel;
import com.cherryjava.basesys.system.user.model.UserModelFactory;
import com.cherryjava.common.core.page.PageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjamm
 */
@Service
public class RoleApplicationService {

    private final RoleModelFactory roleModelFactory;

    private final UserModelFactory userModelFactory;

    private final SysRoleService roleService;

    private final SysUserService userService;

    private final SysMenuService menuService;

    public RoleApplicationService(RoleModelFactory roleModelFactory, UserModelFactory userModelFactory, SysRoleService roleService, SysUserService userService, SysMenuService menuService) {
        this.roleModelFactory = roleModelFactory;
        this.userModelFactory = userModelFactory;
        this.roleService = roleService;
        this.userService = userService;
        this.menuService = menuService;
    }

    public PageDTO<RoleDTO> getRoleList(RoleQuery query) {
        Page<SysRoleEntity> page = roleService.page(query.toPage(), query.toQueryWrapper());
        List<RoleDTO> records = page.getRecords().stream().map(RoleDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public RoleDTO getRoleInfo(Long roleId) {
        SysRoleEntity byId = roleService.getById(roleId);
        RoleDTO roleDTO = new RoleDTO(byId);
        List<Long> selectedDeptList = StrUtil.split(byId.getDeptIdSet(), ",")
                .stream().filter(StrUtil::isNotEmpty).map(Long::new).collect(Collectors.toList());
        roleDTO.setSelectedDeptList(selectedDeptList);
        roleDTO.setSelectedMenuList(menuService.getMenuIdsByRoleId(roleId));
        return roleDTO;
    }


    public void addRole(AddRoleCommand addCommand) {
        RoleModel roleModel = roleModelFactory.create();
        roleModel.loadAddCommand(addCommand);

        roleModel.checkRoleNameUnique();
        roleModel.checkRoleKeyUnique();

        roleModel.insert();
    }

    public void deleteRoleByBulk(List<Long> roleIds) {
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                RoleModel roleModel = roleModelFactory.loadById(roleId);

                roleModel.checkRoleCanBeDelete();

                roleModel.deleteById();
            }
        }
    }


    public void updateRole(UpdateRoleCommand updateCommand) {
        RoleModel roleModel = roleModelFactory.loadById(updateCommand.getRoleId());
        roleModel.loadUpdateCommand(updateCommand);

        roleModel.checkRoleKeyUnique();
        roleModel.checkRoleNameUnique();

        roleModel.updateById();
    }

    public void updateStatus(UpdateStatusCommand command) {
        RoleModel roleModel = roleModelFactory.loadById(command.getRoleId());

        roleModel.setStatus(command.getStatus());

        roleModel.updateById();
    }

    public void updateDataScope(UpdateDataScopeCommand command) {
        RoleModel roleModel = roleModelFactory.loadById(command.getRoleId());

        roleModel.setDeptIds(command.getDeptIds());
        roleModel.setDataScope(command.getDataScope());
        roleModel.generateDeptIdSet();

        roleModel.updateById();
    }


    public PageDTO<UserDTO> getAllocatedUserList(AllocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.getUserListByRole(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO<>(dtoList, page.getTotal());
    }

    public PageDTO<UserDTO> getUnallocatedUserList(UnallocatedRoleQuery query) {
        Page<SysUserEntity> page = userService.getUserListByRole(query);
        List<UserDTO> dtoList = page.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO<>(dtoList, page.getTotal());
    }

    public void deleteRoleOfUserByBulk(List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return;
        }

        for (Long userId : userIds) {
            LambdaUpdateWrapper<SysUserEntity> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.set(SysUserEntity::getRoleId, null).eq(SysUserEntity::getUserId, userId);

            userService.update(updateWrapper);

            CacheCenter.userCache.delete(userId);
        }
    }

    public void addRoleOfUserByBulk(Long roleId, List<Long> userIds) {
        if (CollUtil.isEmpty(userIds)) {
            return;
        }

        RoleModel roleModel = roleModelFactory.loadById(roleId);
        roleModel.checkRoleAvailable();

        for (Long userId : userIds) {
            UserModel user = userModelFactory.loadById(userId);
            user.setRoleId(roleId);
            user.updateById();

            CacheCenter.userCache.delete(userId);
        }
    }


}
