package com.cherryjava.basesys.system.role.command;

/**
 * @author hjamm
 */
public class UpdateStatusCommand {

    private Long roleId;

    private Integer status;

    public UpdateStatusCommand() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
