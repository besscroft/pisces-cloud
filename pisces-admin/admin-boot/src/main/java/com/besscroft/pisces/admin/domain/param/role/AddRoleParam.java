package com.besscroft.pisces.admin.domain.param.role;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 新增角色请求参数
 * @Author Bess Croft
 * @Date 2022/4/3 21:23
 */
@Data
public class AddRoleParam {

    /** 角色名称 */
    @NotEmpty(message = "角色名称不能为空！")
    private String roleName;

    /** 角色编码 */
    @NotEmpty(message = "角色编码不能为空！")
    private String roleCode;

    /** 描述 */
    private String description;

    /** 排序 */
    private Integer sort;

}
