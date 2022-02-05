package com.besscroft.pisces.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 菜单实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:11
 */
@Data
@Builder
@TableName("pisces_auth_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 父级ID */
    @TableField("parent_id")
    private Long parentId;

    /** 菜单名称 */
    @TableField("title")
    private String title;

    /** 前端名称 */
    @TableField("name")
    private String name;

    /** 父菜单名称 */
    @TableField("parent_title")
    private String parentTitle;

    /** 菜单级数 */
    @TableField("level")
    private Integer level;

    /** 组件路径 */
    @TableField("component")
    private String component;

    /** 路由地址 */
    @TableField("path")
    private String path;

    /** 菜单图标名称 */
    @TableField("icon")
    private String icon;

    /** 排序 */
    @TableField("sort")
    private Integer sort;

    /** 创建者 */
    @TableField("creator")
    private String creator;

    /** 更新者 */
    @TableField("updater")
    private String updater;

    /** 创建时间 */
    @TableField("create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /** 菜单显示状态：0->禁用；1->启用 */
    @TableField("hidden")
    private Integer hidden;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField("del")
    private Integer del;

}
