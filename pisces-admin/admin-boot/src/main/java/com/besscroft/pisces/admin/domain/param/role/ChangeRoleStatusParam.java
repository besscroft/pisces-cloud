package com.besscroft.pisces.admin.domain.param.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 角色可用状态更改 请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 20:19
 */
@Data
@Schema(title = "角色可用状态更改请求参数")
public class ChangeRoleStatusParam {

    /** 角色 id */
    @NotNull(message = "角色 id 不能为空")
    @Schema(title = "角色 id", type = "Long", required = true)
    private Long roleId;

    /** 角色可用状态 */
    @NotNull(message = "角色可用状态不能为空")
    @Schema(title = "角色可用状态", type = "Boolean", required = true)
    private Boolean status;

}
