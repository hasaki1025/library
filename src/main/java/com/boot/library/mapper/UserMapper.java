package com.boot.library.mapper;

import com.boot.library.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author pcdn
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-08-15 20:10:28
* @Entity generator.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    int addUser(User user);
    User selectByEmailUser(String email);


}




