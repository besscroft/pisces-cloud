package com.besscroft.pisces.admin.domain.param.resource;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 新增资源 请求参数
 * @Author Bess Croft
 * @Date 2022/4/30 14:23
 */
@Data
public class AddResourceParam {

    /** 资源名称 */
    private String name;

    /** 资源路径 */
    private String url;

    /** 资源描述 */
    private String description;

    /** 资源类别 id */
    @NotNull(message = "资源类别 id 不能为空")
    private Long categoryId;

    /** 排序 */
    private Integer sort;

}
