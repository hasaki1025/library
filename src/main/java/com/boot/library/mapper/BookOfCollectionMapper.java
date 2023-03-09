package com.boot.library.mapper;

import com.boot.library.domain.Book;
import com.boot.library.domain.BookOfCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author pcdn
* @description 针对表【book_of_collection】的数据库操作Mapper
* @createDate 2022-08-15 20:10:28
* @Entity generator.domain.BookOfCollection
*/
public interface BookOfCollectionMapper extends BaseMapper<BookOfCollection> {
    public List<Book> selectBookOfCollection(Integer cId);
}




