package com.boot.library.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

import javax.validation.constraints.Email;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String uId;

    /**
     * 登录邮箱
     */
    @Email
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户角色
     */
    private Integer roleId;

    @Email
    private String KindleEmail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}