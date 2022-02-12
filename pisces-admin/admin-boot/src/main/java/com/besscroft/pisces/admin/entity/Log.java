package com.besscroft.pisces.admin.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

/**
 * @Description 日志实体
 * @Author Bess Croft
 * @Date 2022/2/5 12:12
 */
@Data
@Builder
@Table("pisces_sys_log")
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

}
