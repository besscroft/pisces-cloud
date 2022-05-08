package com.besscroft.pisces.admin.domain.param.resourceCategory;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 更新资源类别 请求参数
 * @Author Bess Croft
 * @Date 2022/5/8 14:50
 */
@Data
public class UpdateResourceCategoryParam {


    /** 资源类别 id */
    @NotNull(message = "资源类别 id 不能为空")
    private Long resourceCategoryId;

    /** 资源类别名称 */
    private String categoryName;

    /** 资源描述 */
    private String description;

    /** 排序 */
    private Integer sort;

}
