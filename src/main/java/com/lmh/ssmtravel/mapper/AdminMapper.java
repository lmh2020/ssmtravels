package com.lmh.ssmtravel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmh.ssmtravel.pojo.Admin;
import com.lmh.ssmtravel.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    Admin findDesc(Integer aid);
    //删除用户所拥有的角色
    void deleteAllRole(Integer id);
    //给用户分配角色
    void addRole(@Param("rid") Integer rid,@Param("pid") Integer pid );
    // 根据管理员名查询权限
    List<Permission> findAllPermission(String username);




}
