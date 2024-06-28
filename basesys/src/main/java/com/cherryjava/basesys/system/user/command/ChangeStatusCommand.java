package com.cherryjava.basesys.system.user.command;

/**
 * @author hjamm
 */
public class ChangeStatusCommand {

    private Long userId;
    private String status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
