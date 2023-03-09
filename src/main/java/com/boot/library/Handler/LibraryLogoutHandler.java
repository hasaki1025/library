package com.boot.library.Handler;

import com.alibaba.fastjson.JSON;
import com.boot.library.Util.JWTUtil;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.LoginUser;
import com.boot.library.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LibraryLogoutHandler implements LogoutSuccessHandler {

    @Autowired
    UserService userService;

    @Autowired
    JWTUtil jwtUtil;



    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Authentication authFromToken = jwtUtil.getAuthFromToken(request);
        if (userService.logout(authFromToken)) {
            response.setHeader("message","logout successfully");
            response.setStatus(200);
        }
        else{
            response.setHeader("message","logout fail");
            response.setStatus(400);
        }
    }
}