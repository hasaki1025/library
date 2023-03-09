package com.boot.library.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 评论
 * @TableName comment
 */
@TableName(value ="comment")
@Data
public class Comment implements Serializable {
    /**
     * 评论ID
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("comId")
    private Integer comId;

    /**
     * 评论内容
     */
    @JsonProperty("comContent")
    private String comContent;

    /**
     * 评论用户ID
     */
    @JsonProperty("uId")
    private String uId;

    /**
     * 被评论书籍ID
     */
    @JsonProperty("bId")
    private Integer bId;

    /**
     * 点赞数
     */
    @JsonProperty("comLike")
    private Integer comLike;

    /**
     * 点踩数
     */
    @JsonProperty("comDislike")
    private Integer comDislike;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}