package com.besscroft.pisces.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Description 角色实体
 * @Author Bess Croft
 * @Date 2022/2/4 15:48
 */
@Data
@Builder
@TableName("pisces_auth_role")
public class Role {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("role_name")
    private String roleName;

    @TableField("role_code")
    private String roleCode;

    @TableField("description")
    private String description;

    @TableField("sort")
    private Integer sort;

    @TableField("creator")
    private String creator;

    @TableField("updater")
    private String updater;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("status")
    private Integer status;

    @TableField("del")
    private Integer del;

}
