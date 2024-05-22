package com.fcls.manager.web.filter;

import com.fcls.manager.domain.entity.UmsSysUser;
import com.fcls.manager.web.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 捕获请求中的请求头，获取token字段，判断是否可以获取用户信息
 * 我们可以继承 OncePerRequestFilter 抽象类
 *
 * 1、获取到用户信息之后，需要将用户的信息告知SpringSecurity，SpringSecurity会去判断你访问的接口是否有相应的权限
 * 2、告知SpringSecurity 就是使用Authentication告知框架，SpringSecurity、会将信息存储到SecurityContext中-----》SecurityContextHolder中
 *
 * 登录的时候，放置的数据是用户名和密码。是要查找用的
 * 后边请求，判断权限的时候，放置进去的数据是用户的信息。密码就不需要了，还有用户的权限
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 该方法会被doFilter调用
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("Authorization");
        System.out.println("token======>" + token);
        // login请求就没token，直接放行，因为后边有其他的过滤器
        if(token == null) {
            doFilter(request,response,filterChain);
            return;
        }
        // 有token，通过jwt工具类，解析用户信息
        Claims claims = null;
        try {
            claims = jwtUtils.parseToken(token);
        }catch (SignatureException e){
            // 需要返回401，重新登陆
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("验签失败！！！");
            return;
        }
        System.out.println("claims======>" + claims);
        // 获取到了数据，将数据取出，放到UmsSysUser中
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);
        String avatar = claims.get("avatar", String.class);
        List<String> perms = claims.get("perms", ArrayList.class);
        // 将信息放到User类中
        UmsSysUser umsSysUser = new UmsSysUser();
        umsSysUser.setId(id);
        umsSysUser.setUsername(username);
        umsSysUser.setAvatar(avatar);
        umsSysUser.setPerms(perms);
        System.out.println("umsSysUser======>" + umsSysUser);
        // 将用户信息放到SecurityContext中
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(umsSysUser, null, umsSysUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 放行
        doFilter(request,response,filterChain);
    }
}
