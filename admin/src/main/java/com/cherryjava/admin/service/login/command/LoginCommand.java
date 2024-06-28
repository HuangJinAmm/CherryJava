package com.cherryjava.admin.service.login.command;


/**
 * 用户登录对象
 *
 * @author hjamm
 */
public class LoginCommand {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String captchaCode;

    /**
     * 唯一标识
     */
    private String captchaCodeKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getCaptchaCodeKey() {
        return captchaCodeKey;
    }

    public void setCaptchaCodeKey(String captchaCodeKey) {
        this.captchaCodeKey = captchaCodeKey;
    }

    @Override
    public String toString() {
        return "LoginCommand{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", captchaCode='" + captchaCode + '\'' +
                ", captchaCodeKey='" + captchaCodeKey + '\'' +
                '}';
    }
}
