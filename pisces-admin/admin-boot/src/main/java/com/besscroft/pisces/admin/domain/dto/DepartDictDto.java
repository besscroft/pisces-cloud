package com.besscroft.pisces.admin.domain.dto;

import lombok.Data;

/**
 * @Description 部门字典封装对象
 * @Author Bess Croft
 * @Date 2022/4/30 17:40
 */
@Data
public class DepartDictDto {

    /** 部门 id */
    private Long departId;

    /** 部门名称 */
    private String departName;

}
