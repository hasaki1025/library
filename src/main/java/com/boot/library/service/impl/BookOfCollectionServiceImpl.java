package com.boot.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.domain.Annotation.MethodNeedCheckAuth;
import com.boot.library.domain.Book;
import com.boot.library.domain.BookOfCollection;
import com.boot.library.domain.Bookcollection;
import com.boot.library.domain.User;
import com.boot.library.service.BookOfCollectionService;
import com.boot.library.mapper.BookOfCollectionMapper;
import com.boot.library.service.BookService;
import com.boot.library.service.BookcollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author pcdn
* @description 针对表【book_of_collection】的数据库操作Service实现
* @createDate 2022-08-15 20:10:28
*/
@Service
public class BookOfCollectionServiceImpl extends ServiceImpl<BookOfCollectionMapper, BookOfCollection>
    implements BookOfCollectionService{

    @Autowired
    BookOfCollectionMapper bookOfCollectionMapper;

    @Autowired
    BookcollectionService bookcollectionService;

    @Autowired
    BookService bookService;




    @Override
    public List<Book> listBookOfCollection(Integer cId) {

        return bookOfCollectionMapper.selectBookOfCollection(cId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBook(BookOfCollection bookOfCollection,User user) {
        Bookcollection byId = bookcollectionService.getById(bookOfCollection.getCId());
        String uId = user.getUId();
        if(byId!=null && byId.getUId().equals(uId))
        {
            Wrapper<BookOfCollection> wrapper=new QueryWrapper<BookOfCollection>()
                    .eq("b_id",bookOfCollection.getBId())
                    .eq("c_id",bookOfCollection.getCId());
            return bookOfCollectionMapper.delete(wrapper)==1;
        }
        return false;
    }

    @Override
    public List<Bookcollection> listByIncludeOneBook(Integer bId) {
        Wrapper<BookOfCollection> wrapper=new QueryWrapper<BookOfCollection>().eq("b_id",bId);
        List<Integer> collect = bookOfCollectionMapper.selectList(wrapper).stream().map(BookOfCollection::getCId).collect(Collectors.toList());
        if(collect.size()==0)
        {
            return new ArrayList<>();
        }
        return bookcollectionService.listByIds(collect);
    }

    @Override
    public boolean addBookToCollection(BookOfCollection bookOfCollection, User user) {
        return bookOfCollectionMapper.insert(bookOfCollection) == 1 && bookService.addCountCollected(bookOfCollection.getBId(), 1);
    }


}




