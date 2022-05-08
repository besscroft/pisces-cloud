package com.besscroft.pisces.admin.domain.param.resourceCategory;

import lombok.Data;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/5/8 14:49
 */
@Data
public class AddResourceCategoryParam {

    /** 资源类别名称 */
    private String categoryName;

    /** 资源描述 */
    private String description;

    /** 排序 */
    private Integer sort;

}
