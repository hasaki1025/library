package com.boot.library.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user_of_collection
 */
@TableName(value ="user_of_collection")
@Data
public class UserOfCollection implements Serializable {
    /**
     * 
     */
    private String uId;

    /**
     * 
     */
    private Integer cId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}