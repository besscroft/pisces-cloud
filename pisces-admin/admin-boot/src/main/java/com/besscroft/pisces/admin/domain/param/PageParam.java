package com.besscroft.pisces.admin.domain.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description 分页列表接口 请求参数
 * @Author Bess Croft
 * @Date 2022/3/13 16:33
 */
@Data
public class PageParam {

    /** 页码 */
    @Min(value = 1, message = "pageNum must be >= 1")
    @NotNull(message = "pageNum must not null")
    private Integer pageNum;

    /** 页大小 */
    @Min(value = 1, message = "pageSize must be >= 1")
    @NotNull(message = "pageSize must not null")
    private Integer pageSize;

}
