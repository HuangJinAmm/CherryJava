package com.cherryjava.basesys.system.user.model;

import com.cherryjava.basesys.system.dept.model.DeptModelFactory;
import com.cherryjava.basesys.system.post.model.PostModelFactory;
import com.cherryjava.basesys.system.role.model.RoleModelFactory;
import com.cherryjava.basesys.system.user.db.SysUserEntity;
import com.cherryjava.basesys.system.user.db.SysUserService;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 用户模型工厂
 *
 * @author hjamm
 */
@Component
public class UserModelFactory {

    private final SysUserService userService;

    private final PostModelFactory postModelFactory;

    private final DeptModelFactory deptModelFactory;

    private final RoleModelFactory roleModelFactory;

    public UserModelFactory(SysUserService userService, PostModelFactory postModelFactory, DeptModelFactory deptModelFactory, RoleModelFactory roleModelFactory) {
        this.userService = userService;
        this.postModelFactory = postModelFactory;
        this.deptModelFactory = deptModelFactory;
        this.roleModelFactory = roleModelFactory;
    }

    public UserModel loadById(Long userId) {
        SysUserEntity byId = userService.getById(userId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, userId, "用户");
        }
        return new UserModel(byId, userService, postModelFactory, deptModelFactory, roleModelFactory);
    }

    public UserModel create() {
        return new UserModel(userService, postModelFactory, deptModelFactory, roleModelFactory);
    }

}
