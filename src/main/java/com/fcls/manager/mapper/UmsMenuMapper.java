package com.fcls.manager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fcls.manager.domain.entity.UmsMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

@Mapper
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {
    Set<UmsMenu> selectMenuByRoleId(@Param("roleIds") Set<Long> roleIds);
}
