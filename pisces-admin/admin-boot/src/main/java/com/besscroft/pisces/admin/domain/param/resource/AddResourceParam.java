package com.besscroft.pisces.admin.domain.param.resource;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description 新增资源 请求参数
 * @Author Bess Croft
 * @Date 2022/4/30 14:23
 */
@Data
@Schema(title = "新增资源请求参数")
public class AddResourceParam {

    /** 资源名称 */
    @Schema(title = "资源名称", type = "String")
    private String name;

    /** 资源路径 */
    @Schema(title = "资源路径", type = "String")
    private String url;

    /** 资源描述 */
    @Schema(title = "资源描述", type = "String")
    private String description;

    /** 路由分配 key */
    @Schema(title = "路由分配 key", type = "String")
    @NotBlank(message = "路由分配 key 不能为空")
    private String routeKey;

    /** 资源类别 id */
    @NotNull(message = "资源类别 id")
    @Schema(title = "登录用户名", type = "Long")
    private Long categoryId;

    /** 排序 */
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
