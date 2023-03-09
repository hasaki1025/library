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
 * @TableName book_of_cate
 */
@TableName(value ="book_of_cate")
@Data
public class BookOfCate implements Serializable {
    /**
     * 
     */
    @JsonProperty("cateId")
    private Integer cateId;

    /**
     * 
     */
    @JsonProperty("bId")
    private Integer bId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}