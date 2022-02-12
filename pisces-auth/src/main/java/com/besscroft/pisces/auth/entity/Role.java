package com.besscroft.pisces.auth.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 角色实体
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
@Data
@Builder
@Table("pisces_auth_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    /** 角色名称 */
    @Column("role_name")
    private String roleName;

    /** 角色编码 */
    @Column("role_code")
    private String roleCode;

    /** 描述 */
    @Column("description")
    private String description;

    /** 排序 */
    @Column("sort")
    private Integer sort;

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

    /** 角色启用状态：0->禁用；1->启用 */
    @Column("status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column("del")
    private Integer del;

}
