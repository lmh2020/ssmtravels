package com.lmh.ssmtravel.controller.backstage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.bean.PermissionWithStatus;
import com.lmh.ssmtravel.pojo.Role;
import com.lmh.ssmtravel.service.RoleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/backstage/role")
public class RoleController {
    @Autowired
    private RoleServer roleServer;

    //分页展示
    @RequestMapping("/all")
    @PreAuthorize("hasAnyAuthority('/role/all')")
    public ModelAndView findByPage(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        Page<Role> rolePage = roleServer.findByPage(page, size);
        modelAndView.addObject("rolePage", rolePage);
        modelAndView.setViewName("/backstage/role_all");
        return modelAndView;
    }

    //添加
    @RequestMapping("/add")
    public String insertRole(Role role) {
        roleServer.insertRole(role);
        return "redirect:/backstage/role/all";
    }

    //删除
    @RequestMapping("/delete")
    public String deleteRole(Integer rid) {
        roleServer.deleteRole(rid);
        return "redirect:/backstage/role/all";
    }

    @RequestMapping("/edit")
    public ModelAndView editRole(Integer rid){
        Role role = roleServer.selectRoleById(rid);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("role",role);
        modelAndView.setViewName("/backstage/role_edit");
        return modelAndView;

    }

    //权限管理
    @RequestMapping("/findPermission")
    public ModelAndView findPermission(Integer rid){
        List<PermissionWithStatus> permissionWithStatuses = roleServer.selectById(rid);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("permissionWithStatuses",permissionWithStatuses);
        modelAndView.addObject("rid",rid);
        modelAndView.setViewName("/backstage/role_permission");
        return modelAndView;
    }
    //修改
    @RequestMapping("/update")
    public String eidt(Integer rid, Integer[] pids){
        roleServer.updateRolePermission(rid,pids);
        return "redirect:/backstage/role/all";
    }


}
