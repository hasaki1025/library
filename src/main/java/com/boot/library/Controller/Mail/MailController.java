package com.boot.library.Controller.Mail;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.Util.MailUtil;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.Book;
import com.boot.library.domain.LoginUser;
import com.boot.library.domain.User;
import com.boot.library.service.BookService;
import com.boot.library.service.UserService;
import org.checkerframework.checker.units.qual.K;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.File;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    MailUtil mailUtil;

    @Autowired
    UserService userService;

    @Autowired
    BookService bookService;


    @GetMapping("/sendMail")
    void sendMail(String mail)
    {
        mailUtil.sendMailCode(mail);
    }

    @PostMapping("/setKindleEmail")
    ResponseEntity<String> setKindleEmail(String KindleEmail)
    {
        try{
            LoginUser loginuser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = loginuser.getUser();
            user.setKindleEmail(KindleEmail);
            userService.updateById(user);
            return new ResponseUtil<String>().addMessage("setKindleEmail successfully", HttpStatus.OK,null);
        }
        catch (Exception e )
        {
            e.printStackTrace();
            return new ResponseUtil<String>().addMessage("setKindleEmail fail", HttpStatus.BAD_REQUEST,null);
        }

    }

    @GetMapping("/sendToKindle")
    public ResponseEntity<String> sendToKindle(Integer  bId) {
        LoginUser loginuser = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = loginuser.getUser();
        Book book = bookService.getById(bId);
        File file = new File(book.getUri());
        if (user.getKindleEmail() == null)
        {
            return new ResponseUtil<String>().addMessage("you have to set you kindle mail",HttpStatus.BAD_REQUEST,null);
        }
        if(file.exists())
        {
            try {
                mailUtil.sendMailWithFile(file,user.getKindleEmail());
                return new ResponseUtil<String>().addMessage("send Book To Kindle successfully",HttpStatus.OK,null);
            } catch (MessagingException | UnsupportedEncodingException e) {
                e.printStackTrace();
                return new ResponseUtil<String>().addMessage("send Book To Kindle fail",HttpStatus.BAD_REQUEST,null);
            }
        }
        return new ResponseUtil<String>().addMessage("this book is not exists",HttpStatus.NOT_FOUND,null);
    }




}
