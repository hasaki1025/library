package com.boot.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.domain.Bookcollection;
import com.boot.library.domain.UserOfCollection;
import com.boot.library.service.UserOfCollectionService;
import com.boot.library.mapper.UserOfCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author pcdn
* @description 针对表【user_of_collection】的数据库操作Service实现
* @createDate 2022-09-01 10:33:11
*/
@Service
public class UserOfCollectionServiceImpl extends ServiceImpl<UserOfCollectionMapper, UserOfCollection>
    implements UserOfCollectionService{

    @Autowired
    UserOfCollectionMapper userOfCollectionMapper;


    @Override
    public List<Bookcollection> getAllCollectionOfUser(String uId) {
        return userOfCollectionMapper.selectCollectionOfUser(uId);
    }
}




