package com.besscroft.pisces.admin.domain.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 新增菜单 请求对象
 * @Author Bess Croft
 * @Date 2022/5/4 18:18
 */
@Data
@Schema(title = "新增菜单请求对象")
public class AddMenuParam {

    /** 父级 id */
    @NotNull(message = "父级菜单 id 不能为空")
    @Schema(title = "父级 id", type = "Long", required = true)
    private Long parentId;

    /** 菜单名称 */
    @Schema(title = "菜单名称", type = "String")
    private String title;

    /** 前端名称 */
    @Schema(title = "前端名称", type = "String")
    private String name;

    /** 菜单级数 */
    @Schema(title = "菜单级数", type = "Integer")
    private Integer level;

    /** 组件路径 */
    @Schema(title = "组件路径", type = "String")
    private String component;

    /** 重定向地址 */
    @Schema(title = "重定向地址", type = "String")
    private String redirect;

    /** 路由地址 */
    @Schema(title = "路由地址", type = "String")
    private String path;

    /** 菜单图标名称 */
    @Schema(title = "菜单图标名称", type = "String")
    private String icon;

    /** 外链（如果有填地址） */
    @Schema(title = "外链（如果有填地址）", type = "String")
    private String isLink;

    /** 菜单显示状态：0->禁用；1->启用 */
    @NotNull(message = "菜单显示状态必填")
    @Schema(title = "菜单显示状态：0->禁用；1->启用", type = "Integer")
    private Integer isHide;

    /** 是否全屏：0->否；1->是 */
    @NotNull(message = "是否全屏必填")
    @Schema(title = "是否全屏：0->否；1->是", type = "Integer")
    private Integer isFull;

    /** 是否固定在 tabs nav：0->否；1->是 */
    @NotNull(message = "是否固定必填")
    @Schema(title = "是否固定在 tabs nav：0->否；1->是", type = "Integer")
    private Integer isAffix;

    /** 是否缓存：0->否；1->是 */
    @NotNull(message = "是否缓存必填")
    @Schema(title = "是否缓存：0->否；1->是", type = "Integer")
    private Integer isKeepAlive;

    /** 排序 */
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
