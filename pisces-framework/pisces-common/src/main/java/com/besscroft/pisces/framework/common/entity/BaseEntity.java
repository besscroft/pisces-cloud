package com.besscroft.pisces.framework.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 实体类对象通用基础类
 * @Author Bess Croft
 * @Date 2022/8/8 14:32
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 创建者 */
    @Schema(title = "创建者", type = "String")
    private String creator;

    /** 更新者 */
    @Schema(title = "更新者", type = "String")
    private String updater;

    /** 创建时间 */
    @Schema(title = "创建时间", type = "Date")
    private LocalDateTime createTime;

    /** 更新时间 */
    @Schema(title = "更新时间", type = "Date")
    private LocalDateTime updateTime;

}
