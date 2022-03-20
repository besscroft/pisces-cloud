package com.besscroft.pisces.admin.domain.param.role;

import lombok.Data;

/**
 * @Description 角色可用状态更改 请求参数
 * @Author Bess Croft
 * @Date 2022/3/20 20:19
 */
@Data
public class ChangeRoleStatusParam {

    /** 角色 id */
    private Long roleId;

    /** 用户可用状态 */
    private Boolean status;

}
