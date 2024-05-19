package com.fcls.manager.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("ums_role")
public class UmsRole implements Serializable {

    @TableId
    private Long roleId;
    private String roleLabel;
    private String roleName;
    private Integer sort;
    private Integer status;
    @TableLogic
    private Integer deleted;
    private String remark;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
