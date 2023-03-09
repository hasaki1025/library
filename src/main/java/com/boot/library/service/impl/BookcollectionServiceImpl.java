package com.boot.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.domain.Bookcollection;
import com.boot.library.domain.User;
import com.boot.library.domain.UserOfCollection;
import com.boot.library.service.BookcollectionService;
import com.boot.library.mapper.BookcollectionMapper;
import com.boot.library.service.UserOfCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.util.List;

/**
* @author pcdn
* @description 针对表【bookcollection】的数据库操作Service实现
* @createDate 2022-08-15 20:10:28
*/
@Service
public class BookcollectionServiceImpl extends ServiceImpl<BookcollectionMapper, Bookcollection>
    implements BookcollectionService{

    @Autowired
    BookcollectionMapper bookcollectionMapper;

    @Autowired
    BookcollectionService bookcollectionService;
    @Autowired
    UserOfCollectionService userOfCollectionService;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initUserCollection(String uId) {
        Bookcollection bookcollection = new Bookcollection();
        bookcollection.setUId(uId);
        bookcollection.setCName("MyBookCollection");
        UserOfCollection userOfCollection = new UserOfCollection();

        boolean save1 = bookcollectionService.save(bookcollection);
        userOfCollection.setUId(uId);
        userOfCollection.setCId(bookcollection.getCId());
        boolean save2 = userOfCollectionService.save(userOfCollection);
        return save1 && save2;
    }

    @Override
    public boolean addCollection(String cName,String uId) {

        try {
            Bookcollection bookcollection = new Bookcollection();
            bookcollection.setUId(uId);
            bookcollection.setCName(cName);
            if (bookcollectionService.save(bookcollection)) {
                UserOfCollection userOfCollection = new UserOfCollection();
                userOfCollection.setCId(bookcollection.getCId());
                userOfCollection.setUId(uId);
                return userOfCollectionService.save(userOfCollection);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteCollection(Integer cId, User user) {
        try {
            Bookcollection bookcollection = bookcollectionMapper.selectById(cId);

            if(bookcollection!=null && (bookcollection.getUId().equals(user.getUId()) || user.getRoleId().equals(2)))
            {
                return bookcollectionMapper.deleteById(cId)==1;
            }
            return false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateCollectionName(Bookcollection bookcollection) {
        try {
            Bookcollection bookcollection2 = bookcollectionMapper.selectById(bookcollection);
            if (bookcollection.getUId().equals(bookcollection2.getUId())) {
                return bookcollectionMapper.updateById(bookcollection)==1;
            }
            return  false;
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addnOfCollection(Integer cId) {
        return bookcollectionMapper.updateNOfCollectionsByCId(cId,1) == 1;
    }


    @Override
    public boolean deletenOfCollection(Integer cId) {
        return bookcollectionMapper.updateNOfCollectionsByCId(cId,-1) == 1;
    }


}




