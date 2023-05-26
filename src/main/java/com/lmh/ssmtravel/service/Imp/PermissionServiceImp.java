package com.lmh.ssmtravel.service.Imp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.mapper.PermissionMapper;
import com.lmh.ssmtravel.pojo.Permission;
import com.lmh.ssmtravel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImp implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    //分页查询权限01
    @Override
    public Page<Permission> findPermissionByPage(Integer page, Integer size) {
        Page<Permission> pages = permissionMapper.selectPage(new Page<Permission>(page, size), null);
        return pages;
    }

  //新增权限
    @Override
    public void insertPermission(Permission permission) {
        permissionMapper.insert(permission);
    }
    //根据id查询权限信息
    @Override
    public Permission findById(Integer rid) {
        Permission permission = permissionMapper.selectById(rid);
        return permission;
    }
    //修改权限信息
    @Override
    public void updatePermission(Permission permission) {
        permissionMapper.updateById(permission);
    }
    //删除权限
    @Override
    public void deletePermission(Integer id) {
        permissionMapper.deleteById(id);

    }
}
