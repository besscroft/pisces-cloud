package com.besscroft.pisces.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 角色实体
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pisces_auth_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 角色名称 */
    @TableField(value = "role_name")
    private String roleName;

    /** 角色编码 */
    @TableField(value = "role_code")
    private String roleCode;

    /** 描述 */
    @TableField(value = "description")
    private String description;

    /** 排序 */
    @TableField(value = "sort")
    private Integer sort;

    /** 创建者 */
    @TableField(value = "creator")
    private String creator;

    /** 更新者 */
    @TableField(value = "updater")
    private String updater;

    /** 创建时间 */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /** 角色启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    private Integer del;

}
