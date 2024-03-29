package com.besscroft.pisces.framework.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * @Description 字典实体
 * @Author Bess Croft
 * @Date 2022/8/5 14:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName(value = "pisces_sys_dict")
@Schema(title = "字典实体")
public class Dict extends BaseEntity {

    @TableId(type = IdType.AUTO)
    @Schema(title = "字典 id", type = "Long")
    private Long id;

    /** 字典分组 */
    @TableField(value = "group_name")
    @Schema(title = "字典分组", type = "String")
    private String groupName;

    /** 字典 key */
    @TableField(value = "key")
    @Schema(title = "字典 key", type = "String")
    private String key;

    /** 字典值 */
    @TableField(value = "value")
    @Schema(title = "字典值", type = "String")
    private String value;

    /** 备注 */
    @TableField(value = "remark")
    @Schema(title = "备注", type = "String")
    private String remark;

}
