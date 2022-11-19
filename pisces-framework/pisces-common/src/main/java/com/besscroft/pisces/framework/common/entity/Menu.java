package com.besscroft.pisces.framework.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 菜单实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "pisces_auth_menu")
@Schema(title = "菜单实体")
public class Menu extends BaseEntity {

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

    /** 重定向地址 */
    @TableField(value = "redirect")
    @Schema(title = "重定向地址", type = "String")
    private String redirect;

    /** 路由地址 */
    @TableField(value = "path")
    @Schema(title = "路由地址", type = "String")
    private String path;

    /** 菜单图标名称 */
    @TableField(value = "icon")
    @Schema(title = "菜单图标名称", type = "String")
    private String icon;

    /** 外链（如果有填地址） */
    @TableField(value = "is_link")
    @Schema(title = "外链（如果有填地址）", type = "String")
    private String isLink;

    /** 菜单显示状态：0->禁用；1->启用 */
    @TableField(value = "is_hide")
    @Schema(title = "菜单显示状态：0->禁用；1->启用", type = "Integer")
    private Integer isHide;

    /** 是否全屏：0->否；1->是 */
    @TableField(value = "is_full")
    @Schema(title = "是否全屏：0->否；1->是", type = "Integer")
    private Integer isFull;

    /** 是否固定在 tabs nav：0->否；1->是 */
    @TableField(value = "is_affix")
    @Schema(title = "是否固定在 tabs nav：0->否；1->是", type = "Integer")
    private Integer isAffix;

    /** 是否缓存：0->否；1->是 */
    @TableField(value = "is_keep_alive")
    @Schema(title = "是否缓存：0->否；1->是", type = "Integer")
    private Integer isKeepAlive;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
