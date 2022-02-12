package com.besscroft.pisces.admin.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 菜单实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:11
 */
@Data
@Builder
@Table("pisces_auth_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    /** 父级ID */
    @Column("parent_id")
    private Long parentId;

    /** 菜单名称 */
    @Column("title")
    private String title;

    /** 前端名称 */
    @Column("name")
    private String name;

    /** 父菜单名称 */
    @Column("parent_title")
    private String parentTitle;

    /** 菜单级数 */
    @Column("level")
    private Integer level;

    /** 组件路径 */
    @Column("component")
    private String component;

    /** 路由地址 */
    @Column("path")
    private String path;

    /** 菜单图标名称 */
    @Column("icon")
    private String icon;

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

    /** 菜单显示状态：0->禁用；1->启用 */
    @Column("hidden")
    private Integer hidden;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column("del")
    private Integer del;

}
