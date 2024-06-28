package com.cherryjava.basesys.system.user.command;

/**
 * @author hjamm
 */
public class UpdateUserCommand extends AddUserCommand {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
