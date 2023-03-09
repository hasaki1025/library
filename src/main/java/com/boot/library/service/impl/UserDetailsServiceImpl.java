package com.boot.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.domain.LoginUser;
import com.boot.library.domain.User;
import com.boot.library.mapper.RoleMapper;
import com.boot.library.mapper.UserMapper;
import com.boot.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getOne(new QueryWrapper<User>().eq("email",s));
        if(user == null)
        {
            throw new RuntimeException();
        }
        //添加权限信息
        List<String> authority = new ArrayList<>(Collections.singletonList("test"));
        authority.add(roleMapper.selectRoleNameByRid(user.getRoleId()));
        return new LoginUser(user,authority);
    }
}


