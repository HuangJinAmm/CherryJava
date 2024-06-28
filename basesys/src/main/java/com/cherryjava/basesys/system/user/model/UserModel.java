package com.cherryjava.basesys.system.user.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.cherryjava.basesys.system.dept.model.DeptModelFactory;
import com.cherryjava.basesys.system.post.model.PostModelFactory;
import com.cherryjava.basesys.system.role.model.RoleModelFactory;
import com.cherryjava.basesys.system.user.command.AddUserCommand;
import com.cherryjava.basesys.system.user.command.UpdateProfileCommand;
import com.cherryjava.basesys.system.user.command.UpdateUserCommand;
import com.cherryjava.basesys.system.user.command.UpdateUserPasswordCommand;
import com.cherryjava.basesys.system.user.db.SysUserEntity;
import com.cherryjava.basesys.system.user.db.SysUserService;
import com.cherryjava.common.config.ProjectConfig;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import com.cherryjava.framework.user.AuthenticationUtils;
import com.cherryjava.framework.user.web.SystemLoginUser;

import java.util.Objects;

/**
 * @author hjamm
 */
public class UserModel extends SysUserEntity {

    private SysUserService userService;

    private PostModelFactory postModelFactory;

    private DeptModelFactory deptModelFactory;

    private RoleModelFactory roleModelFactory;

    public UserModel() {
    }

    public SysUserService getUserService() {
        return userService;
    }

    public void setUserService(SysUserService userService) {
        this.userService = userService;
    }

    public PostModelFactory getPostModelFactory() {
        return postModelFactory;
    }

    public void setPostModelFactory(PostModelFactory postModelFactory) {
        this.postModelFactory = postModelFactory;
    }

    public DeptModelFactory getDeptModelFactory() {
        return deptModelFactory;
    }

    public void setDeptModelFactory(DeptModelFactory deptModelFactory) {
        this.deptModelFactory = deptModelFactory;
    }

    public RoleModelFactory getRoleModelFactory() {
        return roleModelFactory;
    }

    public void setRoleModelFactory(RoleModelFactory roleModelFactory) {
        this.roleModelFactory = roleModelFactory;
    }

    public UserModel(SysUserEntity entity, SysUserService userService, PostModelFactory postModelFactory,
                     DeptModelFactory deptModelFactory, RoleModelFactory roleModelFactory) {
        this(userService, postModelFactory, deptModelFactory, roleModelFactory);

        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public UserModel(SysUserService userService, PostModelFactory postModelFactory,
                     DeptModelFactory deptModelFactory, RoleModelFactory roleModelFactory) {
        this.userService = userService;
        this.postModelFactory = postModelFactory;
        this.deptModelFactory = deptModelFactory;
        this.roleModelFactory = roleModelFactory;
    }

    public void loadAddUserCommand(AddUserCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "userId");
        }
    }


    public void loadUpdateUserCommand(UpdateUserCommand command) {
        if (command != null) {
            loadAddUserCommand(command);
        }
    }

    public void loadUpdateProfileCommand(UpdateProfileCommand command) {
        if (command != null) {
            this.setSex(command.getSex());
            this.setNickname(command.getNickName());
            this.setPhoneNumber(command.getPhoneNumber());
            this.setEmail(command.getEmail());
        }
    }


    public void checkUsernameIsUnique() {
        if (userService.isUserNameDuplicated(getUsername())) {
            throw new ApiException(ErrorCode.Business.USER_NAME_IS_NOT_UNIQUE);
        }
    }

    public void checkPhoneNumberIsUnique() {
        if (StrUtil.isNotEmpty(getPhoneNumber()) && userService.isPhoneDuplicated(getPhoneNumber(),
                getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_PHONE_NUMBER_IS_NOT_UNIQUE);
        }
    }

    public void checkFieldRelatedEntityExist() {

        if (getPostId() != null) {
            postModelFactory.loadById(getPostId());
        }

        if (getDeptId() != null) {
            deptModelFactory.loadById(getDeptId());
        }

        if (getRoleId() != null) {
            roleModelFactory.loadById(getRoleId());
        }

    }


    public void checkEmailIsUnique() {
        if (StrUtil.isNotEmpty(getEmail()) && userService.isEmailDuplicated(getEmail(), getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_EMAIL_IS_NOT_UNIQUE);
        }
    }

    public void checkCanBeDelete(SystemLoginUser loginUser) {
        if (Objects.equals(getUserId(), loginUser.getUserId())
                || this.getIsAdmin()) {
            throw new ApiException(ErrorCode.Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE);
        }
    }


    public void modifyPassword(UpdateUserPasswordCommand command) {
        if (!AuthenticationUtils.matchesPassword(command.getOldPassword(), getPassword())) {
            throw new ApiException(ErrorCode.Business.USER_PASSWORD_IS_NOT_CORRECT);
        }

        if (AuthenticationUtils.matchesPassword(command.getNewPassword(), getPassword())) {
            throw new ApiException(ErrorCode.Business.USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD);
        }
        setPassword(AuthenticationUtils.encryptPassword(command.getNewPassword()));
    }

    public void resetPassword(String newPassword) {
        setPassword(AuthenticationUtils.encryptPassword(newPassword));
    }

    @Override
    public boolean updateById() {
        if (this.getIsAdmin() && ProjectConfig.isDemoEnabled()) {
            throw new ApiException(ErrorCode.Business.USER_ADMIN_CAN_NOT_BE_MODIFY);
        }

        return super.updateById();
    }


}
