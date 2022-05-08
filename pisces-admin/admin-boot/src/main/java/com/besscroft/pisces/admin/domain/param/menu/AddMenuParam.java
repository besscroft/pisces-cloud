package com.besscroft.pisces.admin.domain.param.menu;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 新增菜单 请求对象
 * @Author Bess Croft
 * @Date 2022/5/4 18:18
 */
@Data
public class AddMenuParam {

    /** 父级 id */
    @NotNull(message = "父级菜单 id 不能为空")
    private Long parentId;

    /** 菜单名称 */
    private String title;

    /** 前端名称 */
    private String name;

    /** 菜单级数 */
    private Integer level;

    /** 组件路径 */
    private String component;

    /** 路由地址 */
    private String path;

    /** 菜单图标名称 */
    private String icon;

    /** 排序 */
    private Integer sort;

}
