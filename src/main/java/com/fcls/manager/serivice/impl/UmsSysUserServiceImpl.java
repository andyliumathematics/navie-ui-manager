package com.fcls.manager.serivice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fcls.manager.domain.dto.LoginParams;
import com.fcls.manager.domain.entity.UmsSysUser;
import com.fcls.manager.mapper.UmsSysUserMapper;
import com.fcls.manager.serivice.IUmsSysUserService;
import com.fcls.manager.web.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {
    Logger log = LoggerFactory.getLogger(this.getClass());

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UmsSysUserServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    /**
     * 间接调用 loadUserByUsername方法
     * @param loginParams
     * @return
     */
    @Override
    public String login(LoginParams loginParams) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginParams.getUsername(), loginParams.getPassword());
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(authenticationToken);
        }catch (AuthenticationException e) {
            log.info("用户名或密码错误！");
            return "";
        }
        // 就获取用户信息，返回token
        UmsSysUser umsSysUser = (UmsSysUser) authentication.getPrincipal();
        if(umsSysUser == null) {
            return "用户名或密码错误! ";
        }
        // 将用户信息通过JWT生成token，返回给前端
        Map<String, Object> map = new HashMap<>();
        map.put("id",umsSysUser.getId());
        map.put("username",umsSysUser.getUsername());
        map.put("avatar",umsSysUser.getAvatar());
        map.put("perms",umsSysUser.getPerms());
        return jwtUtils.createToken(map);
    }
}
