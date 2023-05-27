package com.lmh.ssmtravel.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.bean.RoleWithStatus;
import com.lmh.ssmtravel.pojo.Admin;
import com.lmh.ssmtravel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/backstage/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/all")
    @PreAuthorize("hasAnyAuthority('/admin/all')")//权限控制
    public ModelAndView findByPages(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Admin> byPage = adminService.findByPage(page, size);
        modelAndView.addObject("byPage", byPage);
        modelAndView.setViewName("/backstage/admin_all");
        return modelAndView;
    }

    @RequestMapping("/add")
    public String add(Admin admin) {
        adminService.add(admin);
        return "redirect:/backstage/admin/all";
    }

    @RequestMapping("/edit")
    public ModelAndView selectById(@RequestParam("aid") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
            Admin admin = adminService.findById(id);
        modelAndView.addObject("admin", admin);
        modelAndView.setViewName("/backstage/admin_edit");
        return modelAndView;

    }

    @RequestMapping("/update")
    public String updata(Admin admin) {
        adminService.update(admin);
        return "redirect:/backstage/admin/all";
    }

    @RequestMapping("/desc")
    public ModelAndView findDesc(Integer aid) {
        ModelAndView modelAndView = new ModelAndView();
        Admin admin = adminService.findDesc(aid);
        modelAndView.addObject("admin", admin);
        modelAndView.setViewName("/backstage/admin_desc");
        return modelAndView;
    }

    @RequestMapping("/findRole")
    public ModelAndView findRole(Integer aid) {
        ModelAndView modelAndView=new ModelAndView();
        List<RoleWithStatus> roles = adminService.findAllRole(aid);
        modelAndView.addObject("roles",roles);
        modelAndView.addObject("aid",aid);
        modelAndView.setViewName("/backstage/admin_role");
        return modelAndView;
    }
    //修改用户角色
    @RequestMapping("/updateRole")
    public String updateRole(Integer aid,Integer[] ids){
        adminService.updateRole(aid,ids);
        return "redirect:/backstage/admin/all";
    }
    //修改用户状态
    @RequestMapping("/updateStatus")
    public String updateStatus(Integer aid){
        Admin admin = adminService.findById(aid);
        admin.setStatus(!admin.isStatus());
        adminService.updateStatus(admin);
        return "redirect:/backstage/admin/all";
    }

}
