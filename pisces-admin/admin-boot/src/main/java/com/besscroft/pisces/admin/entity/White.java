package com.besscroft.pisces.admin.entity;

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
 * @Description 白名单实体
 * @Author Bess Croft
 * @Date 2022/5/14 18:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pisces_sys_white")
public class White implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 白名单规则名称 */
    @TableField(value = "title")
    private String title;

    /** 白名单规则地址 */
    @TableField(value = "path")
    private String path;

    /** 备注 */
    @TableField(value = "remark")
    private String remark;

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

    /** 白名单规则启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    private Integer del;

}
