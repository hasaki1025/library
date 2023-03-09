package com.boot.library.AOP;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.Book;
import com.boot.library.domain.BookOfCate;
import com.boot.library.domain.LibraryException.CategoryException;
import com.boot.library.domain.LoginUser;
import com.boot.library.service.BookService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
@Aspect
public class CategoryAOP {


    @Autowired
    BookService bookService;

    @Before("execution(* com.boot.library.Controller.Category.BookOfCategoryController.addCateToBook(..))")
    void checkUserOfBookWithAdd(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        List list = (List) args[0];
        List<BookOfCate> arg = new ArrayList<>();
        for (Object o : list) {
            arg.add((BookOfCate) o);
        }
        Integer bId = arg.get(0).getBId();
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uId = user.getUser().getUId();
        Wrapper<Book> wrapper=new QueryWrapper<Book>().eq("b_id",bId).eq("u_id",uId);
        if (bookService.getOne(wrapper)==null) {
            throw new CategoryException();
        }
    }

    @Before("execution(* com.boot.library.Controller.Category.BookOfCategoryController.deleteCateOfBook(..))")
    void checkUserOfBookWithDelete(JoinPoint joinPoint)
    {
        Object[] args = joinPoint.getArgs();
        System.out.println(args[0]);
        BookOfCate bookOfCate = (BookOfCate) args[0];
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String uId = user.getUser().getUId();
        Wrapper<Book> wrapper=new QueryWrapper<Book>().eq("b_id",bookOfCate.getBId()).eq("u_id",uId);
        if (bookService.getOne(wrapper)==null) {
            throw new CategoryException();
        }
    }

}
