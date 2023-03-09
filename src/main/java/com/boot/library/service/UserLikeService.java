package com.boot.library.service;

import com.boot.library.domain.User;
import com.boot.library.domain.UserLike;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author pcdn
* @description 针对表【user_like】的数据库操作Service
* @createDate 2022-09-05 15:24:57
*/
public interface UserLikeService extends IService<UserLike> {
    public void action(UserLike userLike);
    public void cancel_action(UserLike userLike);

    public boolean addOrUpdate(UserLike userLike);



}
