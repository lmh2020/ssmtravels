package com.lmh.ssmtravel.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.pojo.Category;
import com.lmh.ssmtravel.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/backstage/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //首页分页展示数据
    @RequestMapping("/all")
    public ModelAndView findByPage(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Category> CategoryPage = categoryService.findByPage(page, size);
        modelAndView.addObject("CategoryPage", CategoryPage);
        modelAndView.setViewName("/backstage/category_all.html");

        return modelAndView;
    }
}
