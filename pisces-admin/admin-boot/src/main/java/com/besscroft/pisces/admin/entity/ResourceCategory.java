package com.besscroft.pisces.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 资源类别实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:12
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pisces_auth_resource_category")
public class ResourceCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 资源类别名称 */
    @Column(name = "category_name")
    private String categoryName;

    /** 资源描述 */
    @Column(name = "description")
    private String description;

    /** 排序 */
    @Column(name = "sort")
    private Integer sort;

    /** 创建者 */
    @Column(name = "creator")
    private String creator;

    /** 更新者 */
    @Column(name = "updater")
    private String updater;

    /** 创建时间 */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @Column(name = "del")
    private String del;

}
