package com.cherryjava.admin.service.login.dto;

/**
 * @author hjamm
 */
public class CaptchaDTO {

    private Boolean isCaptchaOn;
    private String captchaCodeKey;
    private String captchaCodeImg;

    public Boolean getCaptchaOn() {
        return isCaptchaOn;
    }

    public void setIsCaptchaOn(Boolean captchaOn) {
        isCaptchaOn = captchaOn;
    }

    public String getIsCaptchaCodeKey() {
        return captchaCodeKey;
    }

    public void setCaptchaCodeKey(String captchaCodeKey) {
        this.captchaCodeKey = captchaCodeKey;
    }

    public String getCaptchaCodeImg() {
        return captchaCodeImg;
    }

    public void setCaptchaCodeImg(String captchaCodeImg) {
        this.captchaCodeImg = captchaCodeImg;
    }
}
