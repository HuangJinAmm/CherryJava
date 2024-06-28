package com.cherryjava.basesys.system.user.dto;

import com.cherryjava.basesys.system.role.dto.RoleDTO;

/**
 * @author hjamm
 */
public class UserInfoDTO {

    private UserDTO user;
    private RoleDTO role;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "user=" + user +
                ", role=" + role +
                '}';
    }
}
