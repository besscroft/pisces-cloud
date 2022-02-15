package com.besscroft.pisces.admin.domain.dto;

import com.besscroft.pisces.admin.entity.Menu;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 13:20
 */
@Data
@Builder
public class MenuDto {

    private Long id;

    /** 父级ID */
    private Long parentId;

    /** 菜单名称 */
    private String title;

    /** 前端名称 */
    private String name;

    /** 父菜单名称 */
    private String parentTitle;

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

    /** 创建者 */
    private String creator;

    /** 更新者 */
    private String updater;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 菜单显示状态：0->禁用；1->启用 */
    private Integer hidden;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    private Integer del;

    /** 子菜单 **/
    private List<Menu> children;

}