package com.besscroft.pisces.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 用户实体
 * @Author Bess Croft
 * @Date 2022/2/4 13:18
 */
@Data
@Builder
@TableName("pisces_auth_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("avatar")
    private String avatar;

    @TableField("email")
    private String email;

    @TableField("name")
    private String name;

    @TableField("real_name")
    private String realName;

    @TableField("telephone")
    private String telephone;

    @TableField("birthday")
    private LocalDateTime birthday;

    @TableField("sex")
    private Integer sex;

    @TableField("remark")
    private String remark;

    @TableField("creator")
    private String creator;

    @TableField("updater")
    private String updater;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("status")
    private Integer status;

    @TableField("del")
    private Integer del;

}
