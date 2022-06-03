package com.besscroft.pisces.admin.domain.param.role;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Description 更新角色资源接口请求参数
 * @Author Bess Croft
 * @Date 2022/4/3 15:04
 */
@Data
@Schema(title = "更新角色资源接口请求参数")
public class UpdateResourceByRoleParam {

    /** 角色 id */
    @NotNull(message = "角色 id 不能为空")
    @Schema(title = "角色 id", type = "Long", required = true)
    private Long roleId;

    /** 资源 id 列表 */
    @ArraySchema(arraySchema = @Schema(
            title = "资源 id 列表",
            type = "Long"
    ))
    private Set<Long> resourceIds;

}
