package com.cherryjava.basesys.system.user;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cherryjava.basesys.common.cache.CacheCenter;
import com.cherryjava.basesys.common.command.BulkOperationCommand;
import com.cherryjava.basesys.common.dto.CurrentLoginUserDTO;
import com.cherryjava.basesys.system.post.db.SysPostEntity;
import com.cherryjava.basesys.system.post.db.SysPostService;
import com.cherryjava.basesys.system.post.dto.PostDTO;
import com.cherryjava.basesys.system.role.db.SysRoleEntity;
import com.cherryjava.basesys.system.role.db.SysRoleService;
import com.cherryjava.basesys.system.role.dto.RoleDTO;
import com.cherryjava.basesys.system.user.command.*;
import com.cherryjava.basesys.system.user.db.SearchUserDO;
import com.cherryjava.basesys.system.user.db.SysUserEntity;
import com.cherryjava.basesys.system.user.db.SysUserService;
import com.cherryjava.basesys.system.user.dto.UserDTO;
import com.cherryjava.basesys.system.user.dto.UserDetailDTO;
import com.cherryjava.basesys.system.user.dto.UserProfileDTO;
import com.cherryjava.basesys.system.user.model.UserModel;
import com.cherryjava.basesys.system.user.model.UserModelFactory;
import com.cherryjava.basesys.system.user.query.SearchUserQuery;
import com.cherryjava.common.core.page.PageDTO;
import com.cherryjava.framework.user.web.SystemLoginUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjamm
 */
@Service
public class UserApplicationService {

    private final SysUserService userService;

    private final SysRoleService roleService;

    private final SysPostService postService;

    private final UserModelFactory userModelFactory;

    public UserApplicationService(SysUserService userService, SysRoleService roleService, SysPostService postService, UserModelFactory userModelFactory) {
        this.userService = userService;
        this.roleService = roleService;
        this.postService = postService;
        this.userModelFactory = userModelFactory;
    }

    public PageDTO<UserDTO> getUserList(SearchUserQuery<SearchUserDO> query) {
        Page<SearchUserDO> userPage = userService.getUserList(query);
        List<UserDTO> userDTOList = userPage.getRecords().stream().map(UserDTO::new).collect(Collectors.toList());
        return new PageDTO<>(userDTOList, userPage.getTotal());
    }

    public UserProfileDTO getUserProfile(Long userId) {

        SysUserEntity userEntity = userService.getById(userId);
        SysPostEntity postEntity = userService.getPostOfUser(userId);
        SysRoleEntity roleEntity = userService.getRoleOfUser(userId);

        return new UserProfileDTO(userEntity, postEntity, roleEntity);
    }


    /**
     * 获取当前登录用户信息
     *
     * @return 当前登录用户信息
     */
    public CurrentLoginUserDTO getLoginUserInfo(SystemLoginUser loginUser) {
        CurrentLoginUserDTO permissionDTO = new CurrentLoginUserDTO();

        permissionDTO.setUserInfo(new UserDTO(CacheCenter.userCache.getObjectById(loginUser.getUserId())));
        permissionDTO.setRoleKey(loginUser.getRoleInfo().getRoleKey());
        permissionDTO.setPermissions(loginUser.getRoleInfo().getMenuPermissions());

        return permissionDTO;
    }


    public void updateUserProfile(UpdateProfileCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());
        userModel.loadUpdateProfileCommand(command);

        userModel.checkPhoneNumberIsUnique();
        userModel.checkEmailIsUnique();

        userModel.updateById();

        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public UserDetailDTO getUserDetailInfo(Long userId) {
        SysUserEntity userEntity = userService.getById(userId);
        UserDetailDTO detailDTO = new UserDetailDTO();

        LambdaQueryWrapper<SysRoleEntity> roleQuery = new LambdaQueryWrapper<SysRoleEntity>()
                .orderByAsc(SysRoleEntity::getRoleSort);
        List<RoleDTO> roleDtoList = roleService.list(roleQuery).stream().map(RoleDTO::new).collect(Collectors.toList());
        List<PostDTO> postDtoList = postService.list().stream().map(PostDTO::new).collect(Collectors.toList());
        detailDTO.setRoleOptions(roleDtoList);
        detailDTO.setPostOptions(postDtoList);

        if (userEntity != null) {
            detailDTO.setUser(new UserDTO(userEntity));
            detailDTO.setRoleId(userEntity.getRoleId());
            detailDTO.setPostId(userEntity.getPostId());
        }
        return detailDTO;
    }

    public void addUser(AddUserCommand command) {
        UserModel model = userModelFactory.create();
        model.loadAddUserCommand(command);

        model.checkUsernameIsUnique();
        model.checkPhoneNumberIsUnique();
        model.checkEmailIsUnique();
        model.checkFieldRelatedEntityExist();
        model.resetPassword(command.getPassword());

        model.insert();
    }

    public void updateUser(UpdateUserCommand command) {
        UserModel model = userModelFactory.loadById(command.getUserId());
        model.loadUpdateUserCommand(command);

        model.checkPhoneNumberIsUnique();
        model.checkEmailIsUnique();
        model.checkFieldRelatedEntityExist();
        model.updateById();

        CacheCenter.userCache.delete(model.getUserId());
    }

    public void deleteUsers(SystemLoginUser loginUser, BulkOperationCommand<Long> command) {
        for (Long id : command.getIds()) {
            UserModel userModel = userModelFactory.loadById(id);
            userModel.checkCanBeDelete(loginUser);
            userModel.deleteById();
        }
    }

    public void updatePasswordBySelf(SystemLoginUser loginUser, UpdateUserPasswordCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());
        userModel.modifyPassword(command);
        userModel.updateById();

        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public void resetUserPassword(ResetPasswordCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());

        userModel.resetPassword(command.getPassword());
        userModel.updateById();

        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public void changeUserStatus(ChangeStatusCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());

        userModel.setStatus(Convert.toInt(command.getStatus()));
        userModel.updateById();

        CacheCenter.userCache.delete(userModel.getUserId());
    }

    public void updateUserAvatar(UpdateUserAvatarCommand command) {
        UserModel userModel = userModelFactory.loadById(command.getUserId());

        userModel.setAvatar(command.getAvatar());
        userModel.updateById();

        CacheCenter.userCache.delete(userModel.getUserId());
    }


}
