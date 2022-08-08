package com.besscroft.pisces.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.besscroft.pisces.framework.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 资源实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "pisces_auth_resource")
@Schema(title = "资源实体")
public class Resource extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "资源 id", type = "Long")
    private Long id;

    /** 资源名称 */
    @TableField(value = "name")
    @Schema(title = "资源名称", type = "String")
    private String name;

    /** 资源路径 */
    @TableField(value = "url")
    @Schema(title = "资源路径", type = "String")
    private String url;

    /** 资源描述 */
    @TableField(value = "description")
    @Schema(title = "资源描述", type = "String")
    private String description;

    /** 资源类别 id */
    @TableField(value = "category_id")
    @Schema(title = "资源类别 id", type = "Long")
    private Long categoryId;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

    /** 路由分配 key */
    @TableField(value = "route_key")
    @Schema(title = "路由分配 key", type = "String")
    private String routeKey;

}
