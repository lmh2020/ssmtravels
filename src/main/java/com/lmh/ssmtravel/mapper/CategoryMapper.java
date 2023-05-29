package com.lmh.ssmtravel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lmh.ssmtravel.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

//旅游产品类型分类
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
