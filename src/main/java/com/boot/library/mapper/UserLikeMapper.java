package com.boot.library.mapper;

import com.boot.library.domain.UserLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author pcdn
* @description 针对表【user_like】的数据库操作Mapper
* @createDate 2022-09-05 15:24:57
* @Entity com.boot.library.domain.UserLike
*/
public interface UserLikeMapper extends BaseMapper<UserLike> {

    public int insertOne(UserLike userLike);


    public int updateOne(UserLike userLike);

}




