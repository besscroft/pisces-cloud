package com.besscroft.pisces.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 菜单实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:11
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pisces_auth_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 父级ID */
    @Column(name = "parent_id")
    private Long parentId;

    /** 菜单名称 */
    @Column(name = "title")
    private String title;

    /** 前端名称 */
    @Column(name = "name")
    private String name;

    /** 父菜单名称 */
    @Column(name = "parent_title")
    private String parentTitle;

    /** 菜单级数 */
    @Column(name = "level")
    private Integer level;

    /** 组件路径 */
    @Column(name = "component")
    private String component;

    /** 路由地址 */
    @Column(name = "path")
    private String path;

    /** 菜单图标名称 */
    @Column(name = "icon")
    private String icon;

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

    /** 菜单显示状态：0->禁用；1->启用 */
    @Column(name = "hidden")
    private Integer hidden;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column(name = "del")
    private Integer del;

}
