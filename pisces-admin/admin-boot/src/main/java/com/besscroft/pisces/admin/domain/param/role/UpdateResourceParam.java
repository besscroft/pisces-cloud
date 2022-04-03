package com.besscroft.pisces.admin.domain.param.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Description 更新角色资源接口请求参数
 * @Author Bess Croft
 * @Date 2022/4/3 15:04
 */
@Data
public class UpdateResourceParam {

    /** 角色 id */
    @NotNull(message = "角色 id 不能为空")
    private Long roleId;

    /** 资源 id 列表 */
    private Set<Long> resourceIds;

}
