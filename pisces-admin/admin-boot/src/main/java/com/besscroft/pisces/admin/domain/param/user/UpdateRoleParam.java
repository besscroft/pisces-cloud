package com.besscroft.pisces.admin.domain.param.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @Description 更新用户角色 请求参数
 * @Author Bess Croft
 * @Date 2022/4/10 20:54
 */
@Data
public class UpdateRoleParam {

    /** 用户 id */
    @NotNull(message = "用户 id 不允许为空！")
    private Long userId;

    /** 角色 id 列表 */
    private Set<Long> roleIds;

}
