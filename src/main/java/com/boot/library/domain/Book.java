package com.boot.library.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName book
 */
@TableName(value ="book")
@Data
public class Book implements Serializable {
    /**
     * 书ID
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("bId")
    private Integer bId;

    /**
     * 书名
     */
    @JsonProperty("bName")
    private String bName;

    /**
     * 编写语言
     */
    @JsonProperty("language")
    private String language;

    /**
     * 索书号
     */
    @TableField("ISBN")
    @JsonProperty("ISBN")
    private String ISBN;

    /**
     * 作者
     */
    @JsonProperty("author")
    private String author;

    /**
     * 出版社
     */
    @JsonProperty("publishingHouse")
    private String publishingHouse;

    /**
     * 出版年份
     */
    @JsonProperty("yearOfPublication")
    private String yearOfPublication;

    /**
     * 基本描述
     */
    @JsonProperty("description")
    private String description;

    /**
     * 是否已被删除
     */

    @JsonProperty("hasBeenDeleted")
    @TableLogic
    private Integer hasBeenDeleted;

    /**
     * 上传用户ID
     */
    @JsonProperty("uId")
    private String uId;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("filetype")
    private String filetype;
    @JsonProperty("uNickName")
    private String uNickName;

    @JsonProperty("countCollected")
    private Integer countCollected;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}