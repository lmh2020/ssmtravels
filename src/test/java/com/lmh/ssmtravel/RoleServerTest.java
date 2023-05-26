package com.lmh.ssmtravel;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lmh.ssmtravel.pojo.Role;
import com.lmh.ssmtravel.service.RoleServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleServerTest {
    @Autowired
    private RoleServer roleServer;


    @Test
    void contextLoads() {
        Page<Role> byPage = roleServer.findByPage(1, 3);
        System.out.println(byPage);


    }
}
