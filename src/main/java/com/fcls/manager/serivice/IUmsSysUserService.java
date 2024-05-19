package com.fcls.manager.serivice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fcls.manager.domain.dto.LoginParams;
import com.fcls.manager.domain.entity.UmsSysUser;

public interface IUmsSysUserService extends IService<UmsSysUser> {
    String login(LoginParams loginParams);

}
