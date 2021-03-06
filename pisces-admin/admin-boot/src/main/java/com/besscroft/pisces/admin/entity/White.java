package com.besscroft.pisces.admin.entity;

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
 * @Description 白名单实体
 * @Author Bess Croft
 * @Date 2022/5/14 18:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pisces_sys_white")
@Schema(title = "白名单实体")
public class White implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(title = "白名单 id", type = "Long")
    private Long id;

    /** 白名单规则名称 */
    @TableField(value = "title")
    @Schema(title = "白名单规则名称", type = "String")
    private String title;

    /** 白名单规则地址 */
    @TableField(value = "path")
    @Schema(title = "白名单规则地址", type = "String")
    private String path;

    /** 备注 */
    @TableField(value = "remark")
    @Schema(title = "备注", type = "String")
    private String remark;

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

    /** 白名单规则启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    @Schema(title = "白名单规则启用状态：0->禁用；1->启用", type = "Integer")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
