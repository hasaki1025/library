package com.boot.library.service;

import com.boot.library.domain.Bookcollection;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.library.domain.User;

import java.awt.print.Book;
import java.util.List;

/**
* @author pcdn
* @description 针对表【bookcollection】的数据库操作Service
* @createDate 2022-08-15 20:10:28
*/
public interface BookcollectionService extends IService<Bookcollection> {
    public boolean initUserCollection(String uId);

    public boolean addCollection(String cName,String uId);

    public boolean deleteCollection(Integer cId, User user);

    public  boolean updateCollectionName(Bookcollection bookcollection);


    public boolean addnOfCollection(Integer cId);


    public boolean deletenOfCollection(Integer cId);


}
