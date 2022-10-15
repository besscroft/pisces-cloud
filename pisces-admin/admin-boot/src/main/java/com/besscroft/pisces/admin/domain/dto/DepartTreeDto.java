package com.besscroft.pisces.admin.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @Description 部门树封装对象
 * @Author Bess Croft
 * @Date 2022/10/14 18:01
 */
@Data
public class DepartTreeDto {

    /** 部门 id */
    private Long departId;

    /** 部门名称 */
    private String departName;

    private List<DepartTreeDto> children;

}
