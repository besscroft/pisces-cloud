package com.besscroft.pisces.admin.domain.dto;

import lombok.Data;

/**
 * @Description
 * @Author Bess Croft
 * @Date 2022/4/30 15:42
 */
@Data
public class ResourceCategoryDictDto {

    /** 资源类别 id */
    private Long resourceCategoryId;

    /** 资源类别名称 */
    private String categoryName;

}
