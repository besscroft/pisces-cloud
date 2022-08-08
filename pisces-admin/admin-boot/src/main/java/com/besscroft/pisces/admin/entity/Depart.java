package com.besscroft.pisces.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.besscroft.pisces.framework.common.entity.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 部门实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "pisces_auth_depart")
@Schema(title = "部门实体")
public class Depart extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "部门 id", type = "Long")
    private Long id;

    /** 上级 id */
    @TableField(value = "parent_id")
    @Schema(title = "上级 id", type = "Long")
    private Long parentId;

    /** 部门名称 */
    @TableField(value = "name")
    @Schema(title = "部门名称", type = "String")
    private String name;

    /** 部门描述 */
    @TableField(value = "description")
    @Schema(title = "部门描述", type = "String")
    private String description;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
