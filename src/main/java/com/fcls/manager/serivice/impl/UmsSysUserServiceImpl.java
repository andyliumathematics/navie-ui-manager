package com.fcls.manager.serivice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fcls.manager.domain.dto.LoginParams;
import com.fcls.manager.domain.entity.UmsSysUser;
import com.fcls.manager.mapper.UmsSysUserMapper;
import com.fcls.manager.serivice.IUmsSysUserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UmsSysUserServiceImpl extends ServiceImpl<UmsSysUserMapper, UmsSysUser> implements IUmsSysUserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final AuthenticationManager authenticationManager;

    public UmsSysUserServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 这个认证就需要SpringSecurity帮我们实现了
     * @param loginParams
     * @return
     */
    @Override
    public String login(LoginParams loginParams) {
        // 传入用户名和密码 将是否认证标记设置为false
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(loginParams.getUsername(), loginParams.getPassword());

        // 实现登录逻辑，此时就会去调用 loadUserByUsername方法
        // 返回的 Authentication 其实就是 UserDetails
        Authentication authenticate  = null;
        try{
            authenticate = authenticationManager.authenticate(authentication);
        }catch (AuthenticationException e) {
            log.error("用户名或密码错误！");
            // TODO 抛出一个业务异常
            return "用户名或密码错误！";
        }
        // 获取返回的用户
        UmsSysUser umsSysUser = (UmsSysUser) authenticate.getPrincipal();
        // 生成一个token，返回给前端
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        log.info("登陆后的用户==========》{}",umsSysUser);
        return token;
    }
}
