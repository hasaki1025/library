package com.boot.library.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName user_like
 */
@TableName(value ="user_like")
@Data
public class UserLike implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer lId;

    /**
     * 操作状态（0表示未操作，-1表示点踩，1代表点赞）
     */
    @JsonProperty("action")
    private Integer action;

    /**
     * 点赞人的ID
     */
    @JsonProperty("uId")
    private String uId;

    /**
     * 被点赞的评论的ID
     */
    @JsonProperty("comId")
    private Integer comId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}