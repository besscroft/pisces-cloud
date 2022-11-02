package com.besscroft.pisces.admin.domain.param.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 菜单可用状态更改 请求参数
 * @Author Bess Croft
 * @Date 2022/3/24 15:16
 */
@Data
@Schema(title = "菜单可用状态更改请求参数")
public class ChangeMenuStatusParam {

    /** 菜单 id */
    @Schema(title = "菜单 id", type = "Long", required = true)
    @NotNull(message = "菜单 id 不能为空")
    private Long menuId;

    /** 菜单可用状态 */
    @Schema(title = "菜单可用状态", type = "Boolean", required = true)
    @NotNull(message = "菜单可用状态不能为空")
    private Boolean isHide;

}
