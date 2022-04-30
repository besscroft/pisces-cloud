package com.besscroft.pisces.admin.domain.param.depart;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Description 更新部门 请求参数
 * @Author Bess Croft
 * @Date 2022/4/30 16:43
 */
@Data
public class UpdateDepartParam {

    /** 部门 id */
    @NotNull(message = "部门 id 不能为空")
    private Long departId;

    /** 上级 id */
    @NotNull(message = "没有上级部门 id")
    private Long parentId;

    /** 部门名称 */
    private String name;

    /** 部门描述 */
    private String description;

    /** 排序 */
    private Integer sort;

}
