package com.besscroft.pisces.admin.domain.param.resourceCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description 新增资源类别请求参数
 * @Author Bess Croft
 * @Date 2022/5/8 14:49
 */
@Data
@Schema(title = "新增资源类别请求参数")
public class AddResourceCategoryParam {

    /** 资源类别名称 */
    @Schema(title = "资源类别名称", type = "String")
    private String categoryName;

    /** 资源描述 */
    @Schema(title = "资源描述", type = "String")
    private String description;

    /** 排序 */
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
