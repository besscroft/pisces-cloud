package com.besscroft.pisces.admin.domain.param.menu;

import lombok.Data;

/**
 * @Description 更新菜单请求参数
 * @Author Bess Croft
 * @Date 2022/3/24 15:54
 */
@Data
public class UpdateMenuParam {

    private Long id;

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
