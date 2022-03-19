package com.besscroft.pisces.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 用户实体
 * @Author Bess Croft
 * @Date 2022/2/4 13:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pisces_auth_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 账号(用户名) */
    @TableField(value = "username")
    private String username;

    /** 密码 */
    @TableField(value = "password")
    private String password;

    /** 头像(地址) */
    @TableField(value = "avatar")
    private String avatar;

    /** 邮箱 */
    @TableField(value = "email")
    private String email;

    /** 昵称 */
    @TableField(value = "name")
    private String name;

    /** 真实姓名 */
    @TableField(value = "real_name")
    private String realName;

    /** 手机 */
    @TableField(value = "telephone")
    private String telephone;

    /** 生日 */
    @TableField(value = "birthday")
    private LocalDateTime birthday;

    /** 性别 */
    @TableField(value = "sex")
    private Integer sex;

    /** 备注 */
    @TableField(value = "remark")
    private String remark;

    /** 创建者 */
    @TableField(value = "creator")
    private String creator;

    /** 更新者 */
    @TableField(value = "updater")
    private String updater;

    /** 创建时间 */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /** 帐号启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    private Integer del;

}
