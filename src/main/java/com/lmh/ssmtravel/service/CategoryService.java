package com.lmh.ssmtravel.service;
//旅游产品类型接口
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.pojo.Category;

public interface CategoryService {
    //查询所有分类（分页查询）
    Page<Category> findByPage(Integer page,Integer size);
}
