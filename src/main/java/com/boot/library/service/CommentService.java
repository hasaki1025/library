package com.boot.library.service;

import com.boot.library.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.library.domain.User;

import java.util.List;

/**
* @author pcdn
* @description 针对表【comment(评论)】的数据库操作Service
* @createDate 2022-08-15 20:10:28
*/
public interface CommentService extends IService<Comment> {
    public List<Comment> listComment(Integer bId);

    public void addCommentLike(Integer com_id);

    public boolean addComment(Comment comment);

    public void addCommentDislike(Integer com_id);

    public boolean deleteComment(Comment comment, User user);
}
