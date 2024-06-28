package com.cherryjava.basesys.common.dto;

/**
 * @author hjamm
 */
public class TokenDTO {

    private String token;

    private CurrentLoginUserDTO currentUser;

    public TokenDTO(String token, CurrentLoginUserDTO currentUser) {
        this.token = token;
        this.currentUser = currentUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public CurrentLoginUserDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentLoginUserDTO currentUser) {
        this.currentUser = currentUser;
    }
}
