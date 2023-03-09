package com.boot.library.service;

import com.boot.library.domain.BUC;
import com.boot.library.domain.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.library.domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
* @author pcdn
* @description 针对表【book】的数据库操作Service
* @createDate 2022-08-15 20:10:28
*/
public interface BookService extends IService<Book> {
    public List<Book>  listBook(String keyword);

    void addBook(MultipartFile uploadfile, Book book) throws IOException;

    void deleteBookById(Book book) throws IOException;


    BUC  getOneBUC(Integer bId);

    List<Book> getSomeOneUpload(User user);

    List<Book> getBookByCate(String cateId);

    boolean addCountCollected(Integer bId,Integer n);

    List<Book> getBookByAuthor(String author);
}
