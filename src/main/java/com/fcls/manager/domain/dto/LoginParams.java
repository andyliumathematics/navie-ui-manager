package com.fcls.manager.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 登录参数
 */
@Data
public class LoginParams implements Serializable {

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

    private String username;
    private String password;

}
