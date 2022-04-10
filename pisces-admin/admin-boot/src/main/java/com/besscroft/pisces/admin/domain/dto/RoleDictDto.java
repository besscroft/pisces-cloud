package com.besscroft.pisces.admin.domain.dto;

import lombok.Data;

/**
 * @Description 角色字典 封装对象
 * @Author Bess Croft
 * @Date 2022/4/10 18:39
 */
@Data
public class RoleDictDto {

    /** 角色 id */
    private Long roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色编码 */
    private String roleCode;

}
