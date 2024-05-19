package com.fcls.manager.serivice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fcls.manager.domain.entity.UmsRole;
import com.fcls.manager.mapper.UmsRoleMapper;
import com.fcls.manager.serivice.IUmsRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements IUmsRoleService {

}
