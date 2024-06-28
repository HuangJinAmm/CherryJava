package com.cherryjava.basesys.system.user.command;

/**
 * @author hjamm
 */
public class ResetPasswordCommand {

    private Long userId;
    private String password;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ResetPasswordCommand{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                '}';
    }
}
