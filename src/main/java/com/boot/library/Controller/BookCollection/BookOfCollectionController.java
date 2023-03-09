package com.boot.library.Controller.BookCollection;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.boot.library.Util.ResponseUtil;
import com.boot.library.Util.UserUtil;
import com.boot.library.domain.Book;
import com.boot.library.domain.BookOfCollection;
import com.boot.library.domain.LoginUser;
import com.boot.library.service.BookOfCollectionService;
import com.boot.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collection")
public class BookOfCollectionController {

    @Autowired
    BookOfCollectionService bookOfCollectionService;
    @Autowired
    BookService bookService;
    @Autowired
    UserUtil userUtil;

    @GetMapping("/getBook")
    List<Book> getBookOfCollection(Integer cId) {
        return bookOfCollectionService.listBookOfCollection(cId);
    }

    @GetMapping("/addBook")
    ResponseEntity<String> addBookToCollection(Integer bId,Integer cId) {
        try {
            BookOfCollection collection=new BookOfCollection();
            collection.setBId(bId);
            collection.setCId(cId);
            bookOfCollectionService.addBookToCollection(collection,userUtil.getNowUser());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil<String>().addMessage("add Book fail ", HttpStatus.BAD_REQUEST,null);
        }
        return new ResponseUtil<String>().addMessage("add Book successfully",HttpStatus.OK,null);
    }

    @PostMapping("/deleteBook")
    ResponseEntity<String> deleteBook(@RequestBody BookOfCollection bookOfCollection)
    {
        try {
            if (!bookOfCollectionService.deleteBook(bookOfCollection,userUtil.getNowUser())) {
                throw new RuntimeException();
            }
            bookService.addCountCollected(bookOfCollection.getBId(),-1);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseUtil<String>().addMessage("delete Book fail", HttpStatus.BAD_REQUEST,null);
        }
        return new ResponseUtil<String>().addMessage("delete Book successfully",HttpStatus.OK,null);
    }
}
