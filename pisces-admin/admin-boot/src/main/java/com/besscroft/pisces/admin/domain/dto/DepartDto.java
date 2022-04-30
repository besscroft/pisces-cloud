package com.besscroft.pisces.admin.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Description 部门树封装对象
 * @Author Bess Croft
 * @Date 2022/4/30 16:59
 */
@Data
@Builder
public class DepartDto {

    private Long id;

    /** 上级ID */
    private Long parentId;

    /** 部门名称 */
    private String name;

    /** 部门描述 */
    private String description;

    /** 排序 */
    private Integer sort;

    /** 创建者 */
    private String creator;

    /** 更新者 */
    private String updater;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    private Integer del;

    /** 子部门 */
    private List<DepartDto> children;

}
