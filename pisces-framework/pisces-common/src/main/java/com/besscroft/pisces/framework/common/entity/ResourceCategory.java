package com.besscroft.pisces.framework.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 资源类别实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "pisces_auth_resource_category")
@Schema(title = "资源类别实体")
public class ResourceCategory extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "资源类别 id", type = "Long")
    private Long id;

    /** 资源类别名称 */
    @TableField(value = "category_name")
    @Schema(title = "资源类别名称", type = "String")
    private String categoryName;

    /** 资源描述 */
    @TableField(value = "description")
    @Schema(title = "资源描述", type = "String")
    private String description;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
