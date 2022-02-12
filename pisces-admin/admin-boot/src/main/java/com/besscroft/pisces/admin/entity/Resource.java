package com.besscroft.pisces.admin.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 资源实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:11
 */
@Data
@Builder
@Table("pisces_auth_resource")
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    /** 资源名称 */
    @Column("name")
    private String name;

    /** 资源路径 */
    @Column("url")
    private String url;

    /** 资源描述 */
    @Column("description")
    private String description;

    /** 资源类别ID */
    @Column("category_id")
    private Long categoryId;

    /** 排序 */
    @Column("sort")
    private Integer sort;

    /** 创建者 */
    @Column("creator")
    private String creator;

    /** 更新者 */
    @Column("updater")
    private String updater;

    /** 创建时间 */
    @Column("create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @Column("update_time")
    private LocalDateTime updateTime;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column("del")
    private String del;

}
