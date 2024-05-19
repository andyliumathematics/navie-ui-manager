package com.fcls.manager.web;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fcls.manager.domain.entity.UmsSysUser;
import com.fcls.manager.mapper.UmsSysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UmsSysUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final UmsSysUserMapper sysUserMapper;

    public UmsSysUserDetailsService(UmsSysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    /**
     * 根据用户名查询用户：如果没有查到用户会抛出异常 UsernameNotFoundException【用户名不存在】
     * 返回：UserDetails，SpringSecurity定义的类，用来存储用户信息
     * UmsSysUser：实现了UserDetails接口了，根据多态，它就是一个UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername=========>{}",username);
        UmsSysUser umsSysUser = sysUserMapper.selectOne(new LambdaQueryWrapper<UmsSysUser>().eq(UmsSysUser::getUsername, username));
        log.info("loadUserByUsername=====umsSysUser====>{}",umsSysUser);
        if(umsSysUser != null) {
            // TODO 后期可以查看权限，角色等等  可以用join查询   join查询一般不要超过三张表联查
        }
        return umsSysUser;
    }
}

















