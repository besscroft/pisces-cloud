package com.besscroft.pisces.admin.domain.dto;

/**
 * @Description 角色资源关系对象
 * @Author Bess Croft
 * @Date 2022/3/4 21:31
 */
public interface RoleResourceRelationDto {

    Long getId();


    /** 角色ID */
    Long getRoleId();

    /** 资源ID */
    Long getResourceId();

}
