package com.cherryjava.basesys.system.user.dto;

import com.cherryjava.basesys.system.post.dto.PostDTO;
import com.cherryjava.basesys.system.role.dto.RoleDTO;

import java.util.List;
import java.util.Set;

/**
 * @author hjamm
 */
public class UserDetailDTO {

    private UserDTO user;

    /**
     * 返回所有role
     */
    private List<RoleDTO> roleOptions;

    /**
     * 返回所有posts
     */
    private List<PostDTO> postOptions;

    private Long postId;

    private Long roleId;

    private Set<String> permissions;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<RoleDTO> getRoleOptions() {
        return roleOptions;
    }

    public void setRoleOptions(List<RoleDTO> roleOptions) {
        this.roleOptions = roleOptions;
    }

    public List<PostDTO> getPostOptions() {
        return postOptions;
    }

    public void setPostOptions(List<PostDTO> postOptions) {
        this.postOptions = postOptions;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "UserDetailDTO{" +
                "user=" + user +
                ", roleOptions=" + roleOptions +
                ", postOptions=" + postOptions +
                ", postId=" + postId +
                ", roleId=" + roleId +
                ", permissions=" + permissions +
                '}';
    }
}
