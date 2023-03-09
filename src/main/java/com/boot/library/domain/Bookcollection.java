package com.boot.library.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * @TableName bookcollection
 */
@TableName(value ="bookcollection")
@Data
public class Bookcollection implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    @JsonProperty("cId")
    private Integer cId;

    /**
     * 所属用户ID
     */
    @JsonProperty("uId")
    private String uId;

    /**
     * 收藏夹名称
     */
    @JsonProperty("cName")
    private String cName;

    /**
     * 被收藏数
     */
    @JsonProperty("nOfCollections")
    Integer nOfCollections;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}