package com.boot.library.mapper;

import com.boot.library.domain.Book;
import com.boot.library.domain.Bookcollection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author pcdn
* @description 针对表【bookcollection】的数据库操作Mapper
* @createDate 2022-08-15 20:10:28
* @Entity generator.domain.Bookcollection
*/
public interface BookcollectionMapper extends BaseMapper<Bookcollection> {

    public int insertOneCollection(Bookcollection bookcollection);

    public int updateNOfCollectionsByCId(@Param("cId")Integer cId,@Param("count") Integer count);



}




