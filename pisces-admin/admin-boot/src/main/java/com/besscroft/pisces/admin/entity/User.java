package com.besscroft.pisces.admin.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 用户实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:06
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pisces_auth_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 账号(用户名) */
    @Column(name = "username")
    private String username;

    /** 密码 */
    @Transient
    @Column(name = "password")
    private String password;

    /** 头像(地址) */
    @Column(name = "avatar")
    private String avatar;

    /** 邮箱 */
    @Column(name = "email")
    private String email;

    /** 昵称 */
    @Column(name = "name")
    private String name;

    /** 真实姓名 */
    @Column(name = "real_name")
    private String realName;

    /** 手机 */
    @Column(name = "telephone")
    private String telephone;

    /** 生日 */
    @Column(name = "birthday")
    private LocalDateTime birthday;

    /** 性别 */
    @Column(name = "sex")
    private Integer sex;

    /** 备注 */
    @Column(name = "remark")
    private String remark;

    /** 创建者 */
    @Column(name = "creator")
    private String creator;

    /** 更新者 */
    @Column(name = "updater")
    private String updater;

    /** 创建时间 */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /** 帐号启用状态：0->禁用；1->启用 */
    @Column(name = "status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column(name = "del")
    private Integer del;

    @OneToMany(mappedBy = "user")
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Role> roles;

}
