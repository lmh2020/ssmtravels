package com.lmh.ssmtravel.controller.backstage;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.mapper.PermissionMapper;
import com.lmh.ssmtravel.pojo.Permission;
import com.lmh.ssmtravel.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/backstage/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping("/all")
    @PreAuthorize("hasAnyAuthority('/permission/all')")
    public ModelAndView findByPage(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size){
        ModelAndView modelAndView=new ModelAndView();
        Page<Permission> permission = permissionService.findPermissionByPage(page, size);
        modelAndView.addObject("permission",permission);
        modelAndView.setViewName("/backstage/permission_all");
        return modelAndView;
    }
    @RequestMapping("/add")
    public String addPermission(Permission permission){
        permissionService.insertPermission(permission);
        return "redirect:/backstage/permission/all";
    }
    @RequestMapping("/edit")
    public ModelAndView editPermission(Integer pid){
        ModelAndView modelAndView=new ModelAndView();
        Permission permission = permissionService.findById(pid);
        modelAndView.addObject("permission",permission);
        modelAndView.setViewName("/backstage/permission_edit");
        return modelAndView;
    }
    //修改权限信息
    @RequestMapping("/update")
    public String updatePermission(Permission permission){
        permissionService.updatePermission(permission);
        return "redirect:/backstage/permission/all";
    }
    @RequestMapping("/delete")
    public String deletePermission(Integer pid){
        permissionService.deletePermission(pid);
        return "redirect:/backstage/permission/all";
    }

}
