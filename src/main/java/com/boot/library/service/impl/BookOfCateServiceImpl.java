package com.boot.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.domain.BookOfCate;
import com.boot.library.domain.Category;
import com.boot.library.service.BookOfCateService;
import com.boot.library.mapper.BookOfCateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author pcdn
* @description 针对表【book_of_cate】的数据库操作Service实现
* @createDate 2022-09-01 19:44:56
*/
@Service
public class BookOfCateServiceImpl extends ServiceImpl<BookOfCateMapper, BookOfCate>
    implements BookOfCateService{

    @Autowired
    BookOfCateMapper bookOfCateMapper;


    @Override
    public List<Category> getAllCateOfBook(Integer bId) {
        return bookOfCateMapper.getAllCateBybId(bId);
    }
}




