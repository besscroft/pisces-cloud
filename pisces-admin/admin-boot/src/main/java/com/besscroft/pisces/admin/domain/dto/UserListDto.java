package com.besscroft.pisces.admin.domain.dto;

import com.besscroft.pisces.framework.common.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Description 用户分页列表封装数据
 * @Author Bess Croft
 * @Date 2022/10/14 21:40
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserListDto extends BaseEntity {

    private Long id;

    /** 账号(用户名) */
    @Schema(title = "账号(用户名)", type = "String")
    private String username;

    /** 头像(地址) */
    @Schema(title = "头像(地址)", type = "String")
    private String avatar;

    /** 邮箱 */
    @Schema(title = "邮箱", type = "String")
    private String email;

    /** 昵称 */
    @Schema(title = "昵称", type = "String")
    private String name;

    /** 真实姓名 */
    @Schema(title = "真实姓名", type = "String")
    private String realName;

    /** 手机 */
    @Schema(title = "手机", type = "String")
    private String telephone;

    /** 生日 */
    @Schema(title = "生日", type = "Date")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime birthday;

    /** 性别 */
    @Schema(title = "性别", type = "Integer")
    private Integer sex;

    /** 备注 */
    @Schema(title = "备注", type = "String")
    private String remark;

    /** 帐号启用状态：0->禁用；1->启用 */
    @Schema(title = "帐号启用状态：0->禁用；1->启用", type = "Integer")
    private Integer status;

    /** 部门id */
    @Schema(title = "部门id", type = "Long")
    private Long departId;

    /** 部门名称 */
    @Schema(title = "部门名称", type = "String")
    private String departName;

}
