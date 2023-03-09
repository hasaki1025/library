package com.boot.library.mapper;

import com.boot.library.domain.Bookcollection;
import com.boot.library.domain.UserOfCollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author pcdn
* @description 针对表【user_of_collection】的数据库操作Mapper
* @createDate 2022-09-01 10:33:11
* @Entity com.boot.library.domain.UserOfCollection
*/
public interface UserOfCollectionMapper extends BaseMapper<UserOfCollection> {


    public List<Bookcollection> selectCollectionOfUser(String uId);
}




