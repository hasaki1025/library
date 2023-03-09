package com.boot.library.Controller.User;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CreateCache;
import com.boot.library.Util.MailUtil;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.Util.UserUtil;
import com.boot.library.domain.*;
import com.boot.library.service.BookService;
import com.boot.library.service.UserOfCollectionService;
import com.boot.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    UserOfCollectionService userOfCollectionService;
    @Autowired
    UserUtil userUtil;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailUtil mailUtil;



    @CreateCache(area="TokenCache",name="TokenCache",expire = 2,timeUnit = TimeUnit.HOURS)//expire（过期时间）默认单位为秒
    private Cache<String,String> tokenCache;

    @GetMapping("/user")
    UBC getUser(String uId)
    {
        return userService.getOneUBCByuId(uId);
    }

    @GetMapping("/MyMainPage")
    UBC MyMainPage()
    {
        User nowUser = userUtil.getNowUser();
        return userService.getOneUBCByuId(nowUser.getUId());
    }

    @PostMapping("/changePassword")
    ResponseEntity<String> changePassword(String mail,String code,String newPassword)
    {
        try {
            if(mailUtil.checkCode(mail,code))
            {
                User user = userService.getUserByEmail(mail);
                user.setPassword(passwordEncoder.encode(newPassword));
                if (userService.updateById(user)) {
                    User nowUser = userUtil.getNowUser();
                    if(nowUser!=null)
                    {
                        String uId=nowUser.getUId();
                        tokenCache.remove("Token"+uId);
                    }
                    return new ResponseUtil<String>().addMessage("change password successfully,you need to login", HttpStatus.OK,null);
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseUtil<String>().addMessage("change password fail", HttpStatus.BAD_REQUEST,null);

    }


}
