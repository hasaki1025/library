package com.boot.library.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.library.domain.Book;
import com.boot.library.domain.Comment;
import com.boot.library.domain.User;
import com.boot.library.mapper.BookMapper;
import com.boot.library.service.BookService;
import com.boot.library.service.CommentService;
import com.boot.library.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author pcdn
* @description 针对表【comment(评论)】的数据库操作Service实现
* @createDate 2022-08-15 20:10:28
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Comment> listComment(Integer bId) {
        return commentMapper.selectCommentByLike(bId);
    }

    @Override
    public void addCommentLike(Integer com_id) {
        if(commentMapper.selectById(com_id)!=null)
        {
            commentMapper.updateComLikeByComId(com_id);
        }

    }

    @Override
    public boolean addComment(Comment comment) {

        if (commentMapper.insertOneComment(comment)!=1) {
            return false;
        }
        return true;
    }

    @Override
    public void addCommentDislike(Integer com_id) {
        if(commentMapper.selectById(com_id)!=null)
        {
            commentMapper.updateComDislikeByComId(com_id);
        }
    }

    @Override//user 为当前用户登录的User
    public boolean deleteComment(Comment comment, User user) {
        comment = commentMapper.selectById(comment.getComId());
        if(comment!=null)
        {
            Book book = bookMapper.selectById(comment.getBId());
            if(book !=null  && (book.getUId().equals(user.getUId()) || user.getRoleId().equals(2) || comment.getUId().equals(user.getUId())))
            {
                commentMapper.deleteById(comment.getComId());
                return true;
            }
        }
        return false;
    }

}





