package com.boot.library.service;

import com.boot.library.domain.Bookcollection;
import com.boot.library.domain.UserOfCollection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author pcdn
* @description 针对表【user_of_collection】的数据库操作Service
* @createDate 2022-09-01 10:33:11
*/
public interface UserOfCollectionService extends IService<UserOfCollection> {

    public List<Bookcollection> getAllCollectionOfUser(String uId);

}
