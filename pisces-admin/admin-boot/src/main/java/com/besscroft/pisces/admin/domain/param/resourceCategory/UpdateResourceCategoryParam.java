package com.besscroft.pisces.admin.domain.param.resourceCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 更新资源类别 请求参数
 * @Author Bess Croft
 * @Date 2022/5/8 14:50
 */
@Data
@Schema(title = "客户端登录请求参数")
public class UpdateResourceCategoryParam {


    /** 资源类别 id */
    @NotNull(message = "资源类别 id 不能为空")
    @Schema(title = "资源类别 id", type = "Long", required = true)
    private Long resourceCategoryId;

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
