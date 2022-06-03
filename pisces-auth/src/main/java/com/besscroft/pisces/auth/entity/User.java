package com.besscroft.pisces.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "用户实体")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(title = "用户 id", type = "Long")
    private Long id;

    /** 账号(用户名) */
    @TableField(value = "username")
    @Schema(title = "账号(用户名)", type = "String")
    private String username;

    /** 密码 */
    @TableField(value = "password", select = false)
    @Schema(title = "密码", type = "String")
    private String password;

    /** 头像(地址) */
    @TableField(value = "avatar")
    @Schema(title = "头像(地址)", type = "String")
    private String avatar;

    /** 邮箱 */
    @TableField(value = "email")
    @Schema(title = "邮箱", type = "String")
    private String email;

    /** 昵称 */
    @TableField(value = "name")
    @Schema(title = "昵称", type = "String")
    private String name;

    /** 真实姓名 */
    @TableField(value = "real_name")
    @Schema(title = "真实姓名", type = "String")
    private String realName;

    /** 手机 */
    @TableField(value = "telephone")
    @Schema(title = "手机", type = "String")
    private String telephone;

    /** 生日 */
    @TableField(value = "birthday")
    @Schema(title = "生日", type = "Date")
    private LocalDateTime birthday;

    /** 性别 */
    @TableField(value = "sex")
    @Schema(title = "性别", type = "Integer")
    private Integer sex;

    /** 备注 */
    @TableField(value = "remark")
    @Schema(title = "备注", type = "String")
    private String remark;

    /** 创建者 */
    @TableField(value = "creator")
    @Schema(title = "创建者", type = "String")
    private String creator;

    /** 更新者 */
    @TableField(value = "updater")
    @Schema(title = "更新者", type = "String")
    private String updater;

    /** 创建时间 */
    @TableField(value = "create_time")
    @Schema(title = "创建时间", type = "Date")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(value = "update_time")
    @Schema(title = "更新时间", type = "Date")
    private LocalDateTime updateTime;

    /** 帐号启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    @Schema(title = "帐号启用状态：0->禁用；1->启用", type = "Integer")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
