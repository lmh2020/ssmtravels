package com.lmh.ssmtravel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmh.ssmtravel.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    //查询用户拥有的所有角色的ID
    List<Integer> findRoleId(Integer aid);

    //删除用户的所有权限
    void deleteAll(Integer rid);
    //新增权限
    void  addPermission(@Param("rid") Integer rid, @Param("pid") Integer pid);

}