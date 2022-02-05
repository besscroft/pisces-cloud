package com.besscroft.pisces.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 资源类别实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:12
 */
@Data
@Builder
@TableName("pisces_auth_resource_category")
public class ResourceCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 资源类别名称 */
    @TableField("category_name")
    private String categoryName;

    /** 资源描述 */
    @TableField("description")
    private String description;

    /** 排序 */
    @TableField("sort")
    private Integer sort;

    /** 创建者 */
    @TableField("creator")
    private String creator;

    /** 更新者 */
    @TableField("updater")
    private String updater;

    /** 创建时间 */
    @TableField("create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField("del")
    private String del;

}
