package com.besscroft.pisces.admin.domain.param.depart;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 更新部门 请求参数
 * @Author Bess Croft
 * @Date 2022/4/30 16:43
 */
@Data
@Schema(title = "更新部门请求参数")
public class UpdateDepartParam {

    /** 部门 id */
    @NotNull(message = "部门 id 不能为空")
    @Schema(title = "部门 id", type = "Long", required = true)
    private Long departId;

    /** 上级 id */
    @NotNull(message = "没有上级部门 id")
    @Schema(title = "上级 id", type = "Long", required = true)
    private Long parentId;

    /** 部门名称 */
    @Schema(title = "部门名称", type = "String")
    private String name;

    /** 部门描述 */
    @Schema(title = "部门描述", type = "String")
    private String description;

    /** 排序 */
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
