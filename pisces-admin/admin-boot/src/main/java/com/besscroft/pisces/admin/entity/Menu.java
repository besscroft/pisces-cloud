package com.besscroft.pisces.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "菜单实体")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(title = "菜单 id", type = "Long")
    private Long id;

    /** 父级 id */
    @TableField(value = "parent_id")
    @Schema(title = "父级 id", type = "Long")
    private Long parentId;

    /** 菜单名称 */
    @TableField(value = "title")
    @Schema(title = "菜单名称", type = "String")
    private String title;

    /** 前端名称 */
    @TableField(value = "name")
    @Schema(title = "前端名称", type = "String")
    private String name;

    /** 父菜单名称 */
    @TableField(value = "parent_title")
    @Schema(title = "父菜单名称", type = "String")
    private String parentTitle;

    /** 菜单级数 */
    @TableField(value = "level")
    @Schema(title = "菜单级数", type = "Integer")
    private Integer level;

    /** 组件路径 */
    @TableField(value = "component")
    @Schema(title = "组件路径", type = "String")
    private String component;

    /** 路由地址 */
    @TableField(value = "path")
    @Schema(title = "路由地址", type = "String")
    private String path;

    /** 菜单图标名称 */
    @TableField(value = "icon")
    @Schema(title = "菜单图标名称", type = "String")
    private String icon;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

    /** 创建者 */
    @TableField(value = "creator")
    @Schema(title = "创建者", type = "String")
    private String creator;

    /** 更新者 */
    @TableField(value = "updater")
    @Schema(title = "更新者", type = "String")
    private String updater;

    /** 创建时间 */
    @TableField(value = "create_time")
    @Schema(title = "创建时间", type = "Date")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(value = "update_time")
    @Schema(title = "更新时间", type = "Date")
    private LocalDateTime updateTime;

    /** 菜单显示状态：0->禁用；1->启用 */
    @TableField(value = "hidden")
    @Schema(title = "菜单显示状态：0->禁用；1->启用", type = "Integer")
    private Integer hidden;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
