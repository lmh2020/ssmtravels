package com.lmh.ssmtravel.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.pojo.Permission;

public interface PermissionService {
    //分页查询权限信息
    Page<Permission> findPermissionByPage(Integer page,Integer size);
    //新增权限
    void insertPermission(Permission permission);
    //根据id查询权限信息
    Permission findById(Integer id);
    //修改权限
    void updatePermission(Permission permission);
    //删除权限
    void deletePermission(Integer id);
}
