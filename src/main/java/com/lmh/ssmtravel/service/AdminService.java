package com.lmh.ssmtravel.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.bean.RoleWithStatus;
import com.lmh.ssmtravel.pojo.Admin;
import com.lmh.ssmtravel.pojo.Permission;

import java.util.List;

public interface AdminService {
    //分页查询
    Page<Admin> findByPage(int page,int size);
    //添加用户
    void add(Admin admin);
    //根据id查询用户
    Admin findById(Integer id);
    //修改信息
    void update(Admin admin);
    //查询用户详情
    Admin findDesc(Integer aid);
    //查询用户的角色情况
    List<RoleWithStatus>findAllRole(Integer aid);
    //修改用户角色
    void updateRole(Integer aid,Integer[] rids);
    //修改用户状态
    void updateStatus(Admin admin);
    // 根据名字查询管理员
    Admin findByAdminName(String username);
    // 根据名字查询权限
    List<Permission> findAllPermission(String username);
}
