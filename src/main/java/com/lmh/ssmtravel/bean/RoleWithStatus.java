package com.lmh.ssmtravel.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;


@Data
public class RoleWithStatus {
    @TableId
    private Integer rid;
    private String roleName; // 角色名
    private String roleDesc; // 角色介绍
    private Boolean  adminHas;//用户是否拥有该角色
}
