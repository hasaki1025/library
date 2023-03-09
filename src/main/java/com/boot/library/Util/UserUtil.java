package com.boot.library.Util;

import com.boot.library.domain.LoginUser;
import com.boot.library.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {


    public User getNowUser()
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getUser();
    }

}
