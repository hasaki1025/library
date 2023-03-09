package com.boot.library.Controller.User;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.Util.MailUtil;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.LoginUser;
import com.boot.library.domain.User;
import com.boot.library.mapper.BookcollectionMapper;
import com.boot.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    MailUtil mailUtil;


    @Autowired
    UserService userService;




    @Autowired
    ResponseUtil<String> responseUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    ResponseEntity<String> register(User user, String code)
    {
        if (mailUtil.checkCode(user.getEmail(), code)) {
            if(userService.getUserByEmail(user.getEmail())==null)
            {
                user.setRoleId(2);
                if (userService.Register(user)) {
                    return responseUtil.addMessage("register successfully", HttpStatus.OK,null);
                }
            }
            else
            {
                return responseUtil.addMessage("this email has been registered", HttpStatus.BAD_REQUEST,null);
            }
        }
        return responseUtil.addMessage("you email code is  worry", HttpStatus.BAD_REQUEST,null);
    }




    @PostMapping("/logoff")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> logoff(String password)
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uId=user.getUser().getUId();
        String encode = passwordEncoder.encode(password);
        Wrapper<User> wrapper=new QueryWrapper<User>().eq("u_id",uId);
        User one = userService.getOne(wrapper);

        if (passwordEncoder.matches(password,one.getPassword()) && userService.removeById(uId)) {
            return new ResponseUtil<String>().addMessage("logoff successfully,you can get back you count during one week",HttpStatus.OK,null);
        }
        return new ResponseUtil<String>().addMessage("logoff fail",HttpStatus.BAD_REQUEST,null);
    }


    @Value("${Admin.password}")
    String Adminpwd;

    @PostMapping("/admin/register")
    ResponseEntity<String> registerAdmin(User user,String AdminPassword,String code)
    {
        if(AdminPassword.equals(Adminpwd))
        {
            if (mailUtil.checkCode(user.getEmail(), code)) {
                if(userService.getUserByEmail(user.getEmail())==null)
                {
                    user.setRoleId(1);
                    if (userService.Register(user)) {
                        return responseUtil.addMessage("register successfully", HttpStatus.OK,null);
                    }
                }
                else
                {
                    return responseUtil.addMessage("this email has been registered", HttpStatus.BAD_REQUEST,null);
                }
            }
            return responseUtil.addMessage("you email code is  worry", HttpStatus.BAD_REQUEST,null);
        }
        return responseUtil.addMessage("admin password worry",HttpStatus.UNAUTHORIZED,null);
    }



}
