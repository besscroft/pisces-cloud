package com.besscroft.pisces.framework.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 白名单实体
 * @Author Bess Croft
 * @Date 2022/5/14 18:17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "pisces_sys_white")
@Schema(title = "白名单实体")
public class White extends BaseEntity {

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

    /** 白名单规则启用状态：0->禁用；1->启用 */
    @TableField(value = "status")
    @Schema(title = "白名单规则启用状态：0->禁用；1->启用", type = "Integer")
    private Integer status;

    /** 逻辑删除：0->删除状态；1->可用状态 */
    @TableField(value = "del")
    @Schema(title = "逻辑删除：0->删除状态；1->可用状态", type = "Integer")
    private Integer del;

}
