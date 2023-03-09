package com.boot.library.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.domain.Role;
import com.boot.library.service.RoleService;
import com.boot.library.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author pcdn
* @description 针对表【role】的数据库操作Service实现
* @createDate 2022-08-15 20:10:28
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




