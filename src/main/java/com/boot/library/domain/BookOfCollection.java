package com.boot.library.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName book_of_collection
 */
@TableName(value ="book_of_collection")
@Data
public class BookOfCollection implements Serializable {
    /**
     * 所属收藏夹id
     */
    @JsonProperty("cId")
    private Integer cId;

    /**
     * 被收藏书籍ID
     */
    @JsonProperty("bId")
    private Integer bId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}