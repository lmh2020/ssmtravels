package com.lmh.ssmtravel.security;

import com.lmh.ssmtravel.pojo.Admin;
import com.lmh.ssmtravel.pojo.Permission;
import com.lmh.ssmtravel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//自定义认证授权逻辑
//自定义认证授权逻辑类
@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findByAdminName(username);
        // 1.认证
        if (admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        if (!admin.isStatus()) {
            throw new UsernameNotFoundException("用户不可用");
        }

        // 2.授权
        List<Permission> permissions = adminService.findAllPermission(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permission permission : permissions) {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getPermissionDesc()));
        }
        // 3.封装为UserDetails对象
        UserDetails userDetails = User.withUsername(admin.getUsername())
                .password(admin.getPassword())
                .authorities(grantedAuthorities)
                .build();
        // 4.返回封装好的UserDetails对象
        return userDetails;


    }
}
