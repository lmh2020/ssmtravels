package com.lmh.ssmtravel.service.Imp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.bean.PermissionWithStatus;
import com.lmh.ssmtravel.mapper.PermissionMapper;
import com.lmh.ssmtravel.mapper.RoleMapper;
import com.lmh.ssmtravel.pojo.Permission;
import com.lmh.ssmtravel.pojo.Role;
import com.lmh.ssmtravel.service.RoleServer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServerImp implements RoleServer {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    //分页查询
    @Override
    public Page<Role> findByPage(Integer page, Integer size) {
        Page<Role> rolePage = roleMapper.selectPage(new Page<>(page, size), null);
        return rolePage;
    }
    //添加角色

    @Override
    public void insertRole(Role role) {
        roleMapper.insert(role);
    }
    //删除角色

    @Override
    public void deleteRole(Integer rid) {
        roleMapper.deleteById(rid);
    }

    //根据角色id查询角色
    @Override
    public Role selectRoleById(Integer id) {
        Role role = roleMapper.selectById(id);
        return role;

    }
    //根据角色id查询角色拥有的权限
    @Override
    public List<PermissionWithStatus> selectById(Integer rid) {
        //查询所有权限
        List<Permission> permissions = permissionMapper.selectList(null);
        //查询用户拥有的权限
        List<Integer> pids = permissionMapper.findPermissionById(rid);
        //构建带有状态的权限集合，拥有状态为ture，否则为false
        List<PermissionWithStatus> permissionWithStatuses=new ArrayList<>();
        for(Permission permission:permissions){
            PermissionWithStatus permissionWithStatus=new PermissionWithStatus();
            //将权限数据复制给有状态的权限类
            BeanUtils.copyProperties(permission,permissionWithStatus);
            if (pids.contains(permission.getPid())){
                permissionWithStatus.setRoleHas(true);
            }else {
                permissionWithStatus.setRoleHas(false);
            }
            permissionWithStatuses.add(permissionWithStatus);
        }
        return permissionWithStatuses;
    }
    //根据角色id修改角色权限
    @Override
    public void updateRolePermission(Integer rid, Integer[] pids) {
        roleMapper.deleteAll(rid);
        for (Integer pid:pids){
            roleMapper.addPermission(rid,pid);
        }
    }
}
