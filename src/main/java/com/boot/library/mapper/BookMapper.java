package com.boot.library.mapper;

import com.boot.library.domain.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author pcdn
* @description 针对表【book】的数据库操作Mapper
* @createDate 2022-08-15 20:10:28
* @Entity generator.domain.Book
*/
public interface BookMapper extends BaseMapper<Book> {

    Book selectBookIsExists(Book book);

    void updateOneBook(Book book);

    int insertOneBook(Book book);

    int updateCountCollectedByBId(@Param("bId")Integer bId,@Param("n") Integer n);

}




