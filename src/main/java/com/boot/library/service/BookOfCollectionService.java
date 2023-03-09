package com.boot.library.service;

import com.boot.library.domain.Annotation.MethodNeedCheckAuth;
import com.boot.library.domain.Book;
import com.boot.library.domain.BookOfCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.library.domain.Bookcollection;
import com.boot.library.domain.User;

import java.util.List;

/**
* @author pcdn
* @description 针对表【book_of_collection】的数据库操作Service
* @createDate 2022-08-15 20:10:28
*/
public interface BookOfCollectionService extends IService<BookOfCollection> {
    public List<Book> listBookOfCollection(Integer cId);

    @MethodNeedCheckAuth
    public boolean deleteBook(BookOfCollection bookOfCollection,User user);

    public List<Bookcollection> listByIncludeOneBook(Integer bId);



    @MethodNeedCheckAuth
    public boolean addBookToCollection(BookOfCollection bookOfCollection, User user);


}
