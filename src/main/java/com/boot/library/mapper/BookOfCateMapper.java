package com.boot.library.mapper;

import com.boot.library.domain.BookOfCate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.library.domain.Category;

import java.util.List;

/**
* @author pcdn
* @description 针对表【book_of_cate】的数据库操作Mapper
* @createDate 2022-09-01 19:44:56
* @Entity com.boot.library.domain.BookOfCate
*/
public interface BookOfCateMapper extends BaseMapper<BookOfCate> {


    public int insertById(Integer bId);

    public List<Category> getAllCateBybId(Integer bId);


}




