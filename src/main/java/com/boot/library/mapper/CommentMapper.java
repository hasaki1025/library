package com.boot.library.mapper;

import com.boot.library.domain.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.library.domain.UserLike;

import java.util.List;

/**
* @author pcdn
* @description 针对表【comment(评论)】的数据库操作Mapper
* @createDate 2022-08-15 20:10:28
* @Entity generator.domain.Comment
*/
public interface CommentMapper extends BaseMapper<Comment> {
public void updateComLikeByComId(Integer com_id);


public int insertOneComment(Comment comment);

public int updateComDislikeByComId(Integer com_id);

public List<Comment> selectCommentByLike(Integer bId);


public int updateOneById(Comment comment);



}




