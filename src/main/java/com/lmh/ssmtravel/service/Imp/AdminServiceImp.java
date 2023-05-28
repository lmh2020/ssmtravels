package com.lmh.ssmtravel.service.Imp;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.bean.RoleWithStatus;
import com.lmh.ssmtravel.mapper.AdminMapper;
import com.lmh.ssmtravel.mapper.RoleMapper;
import com.lmh.ssmtravel.pojo.Admin;
import com.lmh.ssmtravel.pojo.Permission;
import com.lmh.ssmtravel.pojo.Role;
import com.lmh.ssmtravel.service.AdminService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public Page<Admin> findByPage(int page, int size) {
        Page pages = adminMapper.selectPage(new Page(page, size), null);
        return pages;
    }

    @Override
    public void add(Admin admin) {
        //给密码加密
        admin.setPassword(encoder.encode(admin.getPassword()));
        adminMapper.insert(admin);
    }

    @Override
    public Admin findById(Integer id) {
        Admin admin = adminMapper.selectById(id);
        return admin;
    }
    //修改用户信息

    @Override
    public void update(Admin admin) {
        //从数据库拿到旧的密码
        String oldPassword = adminMapper.selectById(admin.getAid()).getPassword();
        //新密码
        String newPassword = admin.getPassword();
        // 如果新密码不等于旧密码，对新密码进行加密
        if (!oldPassword.equals(newPassword)){
            admin.setPassword(encoder.encode(newPassword));
        }
        adminMapper.updateById(admin);

    }

    @Override
    public Admin findDesc(Integer aid) {
        Admin admin = adminMapper.findDesc(aid);
        return admin;
    }

    @Override
    public List<RoleWithStatus> findAllRole(Integer aid) {
        //查询所有的角色
        List<Role> roles = roleMapper.selectList(null);

        //查询用户拥有的角色
        List<Integer> roleId = roleMapper.findRoleId(aid);

        //3.构建带有状态的角色集合，拥有状态为ture，否则为false
        List<RoleWithStatus> roleWithStatuses = new ArrayList<>();

        for (Role role : roles) {
            //创建带有状态的对象
            RoleWithStatus roleWithStatus = new RoleWithStatus();
            //复制对象属性
            BeanUtils.copyProperties(role, roleWithStatus);
            if (roleId.contains(role.getRid())) {//判断用户拥有该角色
                roleWithStatus.setAdminHas(true);

            } else {
                roleWithStatus.setAdminHas(false);
            }
            roleWithStatuses.add(roleWithStatus);
        }
        return roleWithStatuses;
    }

    @Override
    public void updateRole(Integer aid, Integer[] ids) {
        adminMapper.deleteAllRole(aid);
        for (Integer rid : ids) {
            adminMapper.addRole(aid, rid);
        }

    }

    @Override
    public void updateStatus(Admin admin) {
        adminMapper.updateById(admin);
    }

    //根据名字查询管理员
    @Override
    public Admin findByAdminName(String username) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        Admin admin = adminMapper.selectOne(wrapper);

        return admin;
    }
    // 根据名字查询权限

    @Override
    public List<Permission> findAllPermission(String username) {
        return adminMapper.findAllPermission(username);
    }


}
