package com.boot.library.mapper;

import com.boot.library.domain.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author pcdn
* @description 针对表【role】的数据库操作Mapper
* @createDate 2022-08-15 20:10:28
* @Entity generator.domain.Role
*/
public interface RoleMapper extends BaseMapper<Role> {
    public String selectRoleNameByRid(int rId);
}




