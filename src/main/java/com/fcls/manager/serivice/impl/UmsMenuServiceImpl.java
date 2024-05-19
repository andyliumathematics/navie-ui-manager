package com.fcls.manager.serivice.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fcls.manager.domain.entity.UmsMenu;
import com.fcls.manager.mapper.UmsMenuMapper;
import com.fcls.manager.serivice.IUmsMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements IUmsMenuService {

}
