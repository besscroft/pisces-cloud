package com.besscroft.pisces.admin.domain.dto;

import lombok.Data;

/**
 * @Description 角色资源关系对象
 * @Author Bess Croft
 * @Date 2022/3/4 21:31
 */
@Data
public class RoleResourceRelationDto {

    private Long id;

    /** 角色ID */
    private Long roleId;

    /** 资源ID */
    private Long resourceId;

}
