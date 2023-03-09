package com.boot.library.ExceptionHandler;


import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.LibraryException.CategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CategoryExceptionHandle {

    @ExceptionHandler(CategoryException.class)
    ResponseEntity<String> handleCategoryException(CategoryException e)
    {
        e.printStackTrace();
        return new ResponseUtil<String>().addMessage("Category change fail", HttpStatus.BAD_REQUEST,null);
    }
}
