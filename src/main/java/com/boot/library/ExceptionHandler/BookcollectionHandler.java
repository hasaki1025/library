package com.boot.library.ExceptionHandler;

import com.boot.library.Util.ResponseUtil;
import com.boot.library.domain.LibraryException.AuthorityException;
import com.boot.library.domain.LibraryException.CategoryException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookcollectionHandler {

    @ExceptionHandler(AuthorityException.class)
    ResponseEntity<String> handleCategoryException(AuthorityException e)
    {
        e.printStackTrace();
        return new ResponseUtil<String>().addMessage("Insufficient permissions", HttpStatus.UNAUTHORIZED,null);
    }
}
