package com.boot.library.service;

import com.boot.library.domain.LoginUser;
import com.boot.library.domain.UBC;
import com.boot.library.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

/**
* @author pcdn
* @description 针对表【user】的数据库操作Service
* @createDate 2022-08-15 20:10:28
*/
public interface UserService extends IService<User> {
        boolean Register(User user);
        User getUserByEmail(String email);

        ResponseEntity<String> login(User user, Long TTL);

        boolean logout(Authentication authentication);


        UBC getOneUBCByuId(String uId);
}
