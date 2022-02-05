package com.besscroft.pisces.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 用户实体
 * @Author Bess Croft
 * @Date 2022/2/4 13:18
 */
@Data
@Builder
@TableName("pisces_auth_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 账号(用户名) */
    @TableField("username")
    private String username;

    /** 密码 */
    @TableField("password")
    private String password;

    /** 头像(地址) */
    @TableField("avatar")
    private String avatar;

    /** 邮箱 */
    @TableField("email")
    private String email;

    /** 昵称 */
    @TableField("name")
    private String name;

    /** 真实姓名 */
    @TableField("real_name")
    private String realName;

    /** 手机 */
    @TableField("telephone")
    private String telephone;

    /** 生日 */
    @TableField("birthday")
    private LocalDateTime birthday;

    /** 性别 */
    @TableField("sex")
    private Integer sex;

    /** 备注 */
    @TableField("remark")
    private String remark;

    /** 创建者 */
    @TableField("creator")
    private String creator;

    /** 更新者 */
    @TableField("updater")
    private String updater;

    /** 创建时间 */
    @TableField("create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /** 帐号启用状态：0->禁用；1->启用 */
    @TableField("status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField("del")
    private Integer del;

}
