package com.boot.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.domain.Category;
import com.boot.library.service.CategoryService;
import com.boot.library.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author pcdn
* @description 针对表【category(类别)】的数据库操作Service实现
* @createDate 2022-09-01 10:32:04
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




