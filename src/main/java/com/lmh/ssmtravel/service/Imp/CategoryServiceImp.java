package com.lmh.ssmtravel.service.Imp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.mapper.CategoryMapper;
import com.lmh.ssmtravel.pojo.Category;
import com.lmh.ssmtravel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//旅游产品类型接口实现
@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    //分页查询数据
    @Override
    public Page<Category> findByPage(Integer page, Integer size) {
        Page<Category> pages = categoryMapper.selectPage(new Page<Category>(page, size), null);
        return pages;
    }
}
