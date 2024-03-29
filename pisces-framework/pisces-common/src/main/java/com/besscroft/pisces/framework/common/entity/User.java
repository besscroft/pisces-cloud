package com.besscroft.pisces.framework.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @Description 用户实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "pisces_auth_user")
@Schema(title = "用户实体")
public class User extends BaseEntity {

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
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime birthday;

    /** 性别 */
    @TableField(value = "sex")
    @Schema(title = "性别", type = "Integer")
    private Integer sex;

    /** 备注 */
    @TableField(value = "remark")
    @Schema(title = "备注", type = "String")
    private String remark;

    /** 帐号启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    @Schema(title = "帐号启用状态：0->禁用；1->启用", type = "Integer")
    private Integer status;

}
