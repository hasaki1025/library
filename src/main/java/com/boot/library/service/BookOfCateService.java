package com.boot.library.service;

import com.boot.library.domain.BookOfCate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.library.domain.Category;

import java.util.List;

/**
* @author pcdn
* @description 针对表【book_of_cate】的数据库操作Service
* @createDate 2022-09-01 19:44:56
*/
public interface BookOfCateService extends IService<BookOfCate> {

    public List<Category> getAllCateOfBook(Integer bId);

}
