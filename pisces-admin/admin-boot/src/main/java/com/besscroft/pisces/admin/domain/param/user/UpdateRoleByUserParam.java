package com.besscroft.pisces.admin.domain.param.user;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Description 更新用户角色 请求参数
 * @Author Bess Croft
 * @Date 2022/4/10 20:54
 */
@Data
@Schema(title = "更新用户角色请求参数")
public class UpdateRoleByUserParam {

    /** 用户 id */
    @NotNull(message = "用户 id 不允许为空！")
    @Schema(title = "用户 id", type = "Long", required = true)
    private Long userId;

    /** 角色 id 列表 */
    @ArraySchema(arraySchema = @Schema(
            title = "角色 id 列表",
            type = "Long"
    ))
    private Set<Long> roleIds;

}
