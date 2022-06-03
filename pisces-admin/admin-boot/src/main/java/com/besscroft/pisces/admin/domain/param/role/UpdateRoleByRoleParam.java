package com.besscroft.pisces.admin.domain.param.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Description 更新角色请求参数
 * @Author Bess Croft
 * @Date 2022/4/3 21:23
 */
@Data
@Schema(title = "更新角色请求参数")
public class UpdateRoleByRoleParam {

    /** 角色 id */
    @NotNull(message = "角色id不能为空！")
    @Schema(title = "角色 id", type = "Long", required = true)
    private Long id;

    /** 角色名称 */
    @NotEmpty(message = "角色名称不能为空！")
    @Schema(title = "角色名称", type = "String", required = true)
    private String roleName;

    /** 角色编码 */
    @NotEmpty(message = "角色编码不能为空！")
    @Schema(title = "角色编码", type = "String", required = true)
    private String roleCode;

    /** 描述 */
    @Schema(title = "描述", type = "String")
    private String description;

    /** 排序 */
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

}
