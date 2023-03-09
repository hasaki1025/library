package com.boot.library.AOP;

import com.boot.library.domain.Annotation.MethodNeedCheckAuth;
import com.boot.library.domain.Book;
import com.boot.library.domain.BookOfCollection;
import com.boot.library.domain.Bookcollection;
import com.boot.library.domain.LibraryException.AuthorityException;
import com.boot.library.domain.User;
import com.boot.library.service.BookOfCollectionService;
import com.boot.library.service.BookService;
import com.boot.library.service.BookcollectionService;
import com.boot.library.service.impl.BookOfCollectionServiceImpl;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class BookOfCollectionAOP {

    @Autowired
    BookService bookService;

    @Autowired
    BookcollectionService bookcollectionService;



    @Before("execution(* com.boot.library.service.impl.BookOfCollectionServiceImpl.*(..))")
    void checkAuthorityOfUser(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        Method[] methods = BookOfCollectionService.class.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName) && method.getAnnotation(MethodNeedCheckAuth.class)==null)
            {
                return;
            }
        }

        Object[] args = joinPoint.getArgs();
        BookOfCollection bookOfCollection = (BookOfCollection) args[0];
        User user = (User) args[1];
        Bookcollection bookcollection = bookcollectionService.getById(bookOfCollection.getCId());
        if(!user.getUId().equals(bookcollection.getUId()) && !user.getRoleId().equals(2))
        {
            throw new AuthorityException("你没有权限修改该书单");
        }
    }
}
