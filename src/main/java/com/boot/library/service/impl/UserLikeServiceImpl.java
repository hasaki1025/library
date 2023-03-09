package com.boot.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.domain.Comment;
import com.boot.library.domain.UserLike;
import com.boot.library.mapper.CommentMapper;
import com.boot.library.service.CommentService;
import com.boot.library.service.UserLikeService;
import com.boot.library.mapper.UserLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

/**
* @author pcdn
* @description 针对表【user_like】的数据库操作Service实现
* @createDate 2022-09-05 15:24:57
*/
@Service
public class UserLikeServiceImpl extends ServiceImpl<UserLikeMapper, UserLike>
    implements UserLikeService{


    @Autowired
    UserLikeService userLikeService;

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserLikeMapper userLikeMapper;


    @Autowired
    Jedis jedis;



    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void action(UserLike userLike) {
        String redisKey="UserLike"+userLike.getUId();
        String redisValue=String.valueOf(userLike.getComId());
        if (!userLikeService.addOrUpdate(userLike)) {
            throw new RuntimeException("action fail");
        }
        if(!jedis.sismember(redisKey,redisValue))
        {
            if(userLike.getAction()==1)
            {
                commentMapper.updateComLikeByComId(userLike.getComId());
            }
            else{
                commentMapper.updateComDislikeByComId(userLike.getComId());
            }
            jedis.sadd(redisKey, redisValue);
        }
        else {
            Comment comment = new Comment();
            comment.setComId(userLike.getComId());
            if(userLike.getAction()==1)
            {
                comment.setComDislike(-1);
                comment.setComLike(1);
                if (commentMapper.updateOneById(comment) != 1) {
                    throw new RuntimeException();
                }

            }
            else{
                comment.setComLike(-1);
                comment.setComDislike(1);
                if (commentMapper.updateOneById(comment) != 1) {
                    throw new RuntimeException();
                }
            }
        }


    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void cancel_action(UserLike userLike) {
        String redisKey="UserLike"+userLike.getUId();
        String redisValue=String.valueOf(userLike.getComId());
        jedis.srem(redisKey,redisValue);
        Comment comment = new Comment();
        comment.setComId(userLike.getComId());

        if(userLike.getAction()==1)
        {
            comment.setComLike(-1);
        }
        else {
            comment.setComDislike(-1);
        }
        if (commentMapper.updateOneById(comment)!=1) {
            throw  new RuntimeException();
        }
        Wrapper<UserLike> wrapper=new QueryWrapper<UserLike>().eq("com_id",userLike.getComId()).eq("u_id",userLike.getUId());
        userLikeMapper.delete(wrapper);
    }

    @Override
    public boolean addOrUpdate(UserLike userLike) {
        Wrapper<UserLike> wrapper=new QueryWrapper<UserLike>().eq("com_id",userLike.getComId()).eq("u_id",userLike.getUId());
        UserLike userLike1 = userLikeMapper.selectOne(wrapper);
        if (userLike1==null) {
            return userLikeMapper.insertOne(userLike) == 1;
        }
        else {
            userLike.setLId(userLike1.getLId());
            return userLikeMapper.updateById(userLike) == 1;
        }
    }
}




