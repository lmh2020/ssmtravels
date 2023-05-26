package com.lmh.ssmtravel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmh.ssmtravel.pojo.Permission;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Integer> findPermissionById(Integer rid);

}
