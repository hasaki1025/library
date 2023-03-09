package com.boot.library.ExceptionHandler;

import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.LibraryException.CategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class libraryHandler {

    @ExceptionHandler(Exception.class)
    void handleCategoryException(Exception e, HttpServletResponse response)
    {
        e.printStackTrace();
        if(response.getStatus()==200)
        {
            response.setStatus(400);
            response.setHeader("message","unknown error");
        }
    }

}
