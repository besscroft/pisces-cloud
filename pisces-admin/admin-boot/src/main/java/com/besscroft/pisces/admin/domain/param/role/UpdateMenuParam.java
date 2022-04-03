package com.besscroft.pisces.admin.domain.param.role;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Description 更新角色菜单接口请求参数
 * @Author Bess Croft
 * @Date 2022/4/3 10:36
 */
@Data
public class UpdateMenuParam {

    /** 角色 id */
    @NotNull(message = "角色 id 不能为空")
    private Long roleId;

    /** 菜单 id 列表 */
    private Set<Long> menuIds;

}
