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
 * @Description 资源实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pisces_auth_resource")
@Schema(title = "资源实体")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

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

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
