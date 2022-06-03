package com.besscroft.pisces.admin.domain.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 用户可用状态更改 请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 11:34
 */
@Data
@Schema(title = "用户可用状态更改请求参数")
public class ChangeUserStatusParam {

    /** 用户 id */
    @Schema(title = "用户 id", type = "Long", required = true)
    @NotNull(message = "用户 id 不能为空")
    private Long userId;

    /** 用户可用状态 */
    @NotNull(message = "用户可用状态不能为空")
    @Schema(title = "用户可用状态", type = "Boolean", required = true)
    private Boolean status;

}
