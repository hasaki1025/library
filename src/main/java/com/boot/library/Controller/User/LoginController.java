package com.boot.library.Controller.User;

import com.boot.library.Util.MailUtil;
import com.boot.library.domain.User;
import com.boot.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @Value("${JWT.FreeLoginTTL}")
    long freeLoginTTL;



    @PostMapping("/login")
    ResponseEntity<String> login(@Valid User user, Integer freeLogin)
    {
        if(freeLogin!=null)
        {
            return userService.login(user,freeLoginTTL);
        }
        else
        {
            return userService.login(user,null);
        }
    }



}
