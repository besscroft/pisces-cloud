package com.besscroft.pisces.admin.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 用户实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:06
 */
@Data
@Builder
@Table("pisces_auth_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    /** 账号(用户名) */
    @Column("username")
    private String username;

    /** 密码 */
    @Column("password")
    private String password;

    /** 头像(地址) */
    @Column("avatar")
    private String avatar;

    /** 邮箱 */
    @Column("email")
    private String email;

    /** 昵称 */
    @Column("name")
    private String name;

    /** 真实姓名 */
    @Column("real_name")
    private String realName;

    /** 手机 */
    @Column("telephone")
    private String telephone;

    /** 生日 */
    @Column("birthday")
    private LocalDateTime birthday;

    /** 性别 */
    @Column("sex")
    private Integer sex;

    /** 备注 */
    @Column("remark")
    private String remark;

    /** 创建者 */
    @Column("creator")
    private String creator;

    /** 更新者 */
    @Column("updater")
    private String updater;

    /** 创建时间 */
    @Column("create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @Column("update_time")
    private LocalDateTime updateTime;

    /** 帐号启用状态：0->禁用；1->启用 */
    @Column("status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column("del")
    private Integer del;

}
