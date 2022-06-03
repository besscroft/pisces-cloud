package com.besscroft.pisces.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(title = "角色实体")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(title = "角色 id", type = "Long")
    private Long id;

    /** 角色名称 */
    @TableField(value = "role_name")
    @Schema(title = "角色名称", type = "String")
    private String roleName;

    /** 角色编码 */
    @TableField(value = "role_code")
    @Schema(title = "角色编码", type = "String")
    private String roleCode;

    /** 描述 */
    @TableField(value = "description")
    @Schema(title = "描述", type = "String")
    private String description;

    /** 排序 */
    @TableField(value = "sort")
    @Schema(title = "排序", type = "Integer")
    private Integer sort;

    /** 创建者 */
    @TableField(value = "creator")
    @Schema(title = "创建者", type = "String")
    private String creator;

    /** 更新者 */
    @TableField(value = "updater")
    @Schema(title = "更新者", type = "String")
    private String updater;

    /** 创建时间 */
    @TableField(value = "create_time")
    @Schema(title = "创建时间", type = "Date")
    private LocalDateTime createTime;

    /** 更新时间 */
    @TableField(value = "update_time")
    @Schema(title = "更新时间", type = "Date")
    private LocalDateTime updateTime;

    /** 角色启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    @Schema(title = "角色启用状态：0->禁用；1->启用", type = "Integer")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
