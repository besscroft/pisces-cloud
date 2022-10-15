package com.besscroft.pisces.admin.domain.param.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 更新用户部门 请求参数
 * @Author Bess Croft
 * @Date 2022/10/15 12:38
 */
@Data
@Schema(title = "更新用户部门请求参数")
public class UpdateDepartByUserParam {

    /** 用户 id */
    @NotNull(message = "用户 id 不允许为空！")
    @Schema(title = "用户 id", type = "Long", required = true)
    private Long userId;

    /** 部门 id */
    @NotNull(message = "部门 id 不允许为空！")
    @Schema(title = "部门 id", type = "Long", required = true)
    private Long departId;

}
