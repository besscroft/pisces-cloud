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
 * @Description
 * @Author Bess Croft
 * @Date 2022/2/5 12:06
 */
@Data
@Builder
@TableName("pisces_auth_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 角色名称 */
    @TableField("role_name")
    private String roleName;

    /** 角色编码 */
    @TableField("role_code")
    private String roleCode;

    /** 描述 */
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

    /** 角色启用状态：0->禁用；1->启用 */
    @TableField("status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField("del")
    private Integer del;

}
