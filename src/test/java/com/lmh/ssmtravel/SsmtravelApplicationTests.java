package com.lmh.ssmtravel;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.bean.RoleWithStatus;
import com.lmh.ssmtravel.pojo.Admin;
import com.lmh.ssmtravel.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SsmtravelApplicationTests {
    @Autowired
    private AdminService adminService;


    @Test
    void contextLoads() {
       Page<Admin> byPage = adminService.findByPage(1, 3);
        System.out.println(byPage);


    }
    @Test
    public void test(){
        Admin desc = adminService.findDesc(1);
        System.out.println(desc);
    }
    @Test
    public void testfind(){
        List<RoleWithStatus> allRole = adminService.findAllRole(1);
        System.out.println(allRole);
    }
    @Test
    public void updateRole(){
        Integer[] i={1,2};
        adminService.updateRole(1,i);
    }

}
