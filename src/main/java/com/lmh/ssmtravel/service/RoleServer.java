package com.lmh.ssmtravel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.bean.PermissionWithStatus;
import com.lmh.ssmtravel.pojo.Role;

import java.util.List;

public interface RoleServer {
    //分页查询
    Page<Role> findByPage(Integer page,Integer size);
    //新增角色
    void insertRole(Role role);


    //根据id查询角色信息
    Role selectRoleById(Integer id);
    //根据角色id,查询角色拥有的权限

    List<PermissionWithStatus> selectById(Integer rid);
    //根据id删除角色
    void deleteRole(Integer rid);
    //修改权限
    void updateRolePermission(Integer rid, Integer[] pids);


}
