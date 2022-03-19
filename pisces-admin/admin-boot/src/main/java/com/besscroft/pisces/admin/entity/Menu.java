package com.besscroft.pisces.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 菜单实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pisces_auth_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 父级ID */
    @TableField(value = "parent_id")
    private Long parentId;

    /** 菜单名称 */
    @TableField(value = "title")
    private String title;

    /** 前端名称 */
    @TableField(value = "name")
    private String name;

    /** 父菜单名称 */
    @TableField(value = "parent_title")
    private String parentTitle;

    /** 菜单级数 */
    @TableField(value = "level")
    private Integer level;

    /** 组件路径 */
    @TableField(value = "component")
    private String component;

    /** 路由地址 */
    @TableField(value = "path")
    private String path;

    /** 菜单图标名称 */
    @TableField(value = "icon")
    private String icon;

    /** 排序 */
    @TableField(value = "sort")
    private Integer sort;

    /** 创建者 */
    @TableField(value = "creator")
    private String creator;

    /** 更新者 */
    @TableField(value = "updater")
    private String updater;

    /** 创建时间 */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /** 菜单显示状态：0->禁用；1->启用 */
    @TableField(value = "hidden")
    private Integer hidden;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    private Integer del;

}
