package com.cherryjava.framework.user.app;

import com.cherryjava.framework.user.base.BaseLoginUser;

/**
 * 登录用户身份权限
 *
 * @author hjamm
 */
public class AppLoginUser extends BaseLoginUser {

    private static final long serialVersionUID = 1L;

    private boolean isVip;

    public AppLoginUser() {
        super();
    }

    public AppLoginUser(Long userId, Boolean isVip, String cachedKey) {
        this.cachedKey = cachedKey;
        this.userId = userId;
        this.isVip = isVip;
    }


    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
