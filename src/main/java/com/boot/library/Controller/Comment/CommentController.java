package com.boot.library.Controller.Comment;

import com.boot.library.Util.ResponseUtil;
import com.boot.library.Util.UserUtil;
import com.boot.library.domain.*;
import com.boot.library.service.CommentService;
import com.boot.library.service.UserLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    CommentService commentService;

    @Autowired
    UserLikeService userLikeService;
    @Autowired
    UserUtil userUtil;

    @GetMapping("/get")
    List<Comment> getComment(Integer bId)
    {
        return commentService.listComment(bId);
    }

    @PostMapping("/do")
    ResponseEntity<String> doComment(@RequestBody Comment comment)
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setUId(user.getUser().getUId());
        if (commentService.addComment(comment)) {
            return new ResponseUtil<String>().addMessage("comment successfully", HttpStatus.OK,null);
        }
        return new ResponseUtil<String>().addMessage("comment fail", HttpStatus.BAD_REQUEST,null);
    }

    @PostMapping("/like")
    void likeAction(@RequestBody UserLikeWithCancel userLikeWithCancel)
    {
        boolean isCancel = userLikeWithCancel.isCancel();
        UserLike userLike = userLikeWithCancel.getUserLike();
        userLike.setUId(userUtil.getNowUser().getUId());

        if(isCancel)
        {
            userLikeService.cancel_action(userLike);
        }
        else {
            userLikeService.action(userLike);
        }
    }

    @PostMapping("/dislike")
    void dislikeAction(@RequestBody UserLikeWithCancel userLikeWithCancel)
    {
        boolean isCancel = userLikeWithCancel.isCancel();
        UserLike userLike = userLikeWithCancel.getUserLike();
        userLike.setUId(userUtil.getNowUser().getUId());
        if(isCancel)
        {
            userLikeService.cancel_action(userLike);
        }
        else {
            userLikeService.action(userLike);
        }
    }

    @GetMapping("/delete")
    ResponseEntity<String> deleteComment(Comment comment)
    {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (commentService.deleteComment(comment,user.getUser())) {
            return new ResponseUtil<String>().addMessage("delete successfully",HttpStatus.OK,null);
        }
        return new ResponseUtil<String>().addMessage("delete fail",HttpStatus.BAD_REQUEST,null);
    }

}
