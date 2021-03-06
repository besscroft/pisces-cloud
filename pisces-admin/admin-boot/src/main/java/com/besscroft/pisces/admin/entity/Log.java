package com.besscroft.pisces.admin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @Description 日志实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "pisces_sys_log")
@Schema(title = "日志实体")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @Schema(title = "日志 id", type = "Long")
    private Long id;

}
