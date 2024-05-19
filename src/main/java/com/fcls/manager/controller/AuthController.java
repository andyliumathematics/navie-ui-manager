package com.fcls.manager.controller;

import com.fcls.manager.domain.dto.LoginParams;
import com.fcls.manager.serivice.IUmsSysUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final IUmsSysUserService sysUserService;

    public AuthController(IUmsSysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 登录方法：返回一个令牌
     * 用户再次访问时，在请求头 header：携带token
     */
    @PostMapping("login")
    public String login(@RequestBody LoginParams loginParams) {
        String token = sysUserService.login(loginParams);
        return token;
    }
}
