package com.besscroft.pisces.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 角色实体
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pisces_auth_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 角色名称 */
    @Column(name = "role_name")
    private String roleName;

    /** 角色编码 */
    @Column(name = "role_code")
    private String roleCode;

    /** 描述 */
    @Column(name = "description")
    private String description;

    /** 排序 */
    @Column(name = "sort")
    private Integer sort;

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

    /** 角色启用状态：0->禁用；1->启用 */
    @Column(name = "status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column(name = "del")
    private Integer del;

}
